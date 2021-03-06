package project.se3354.sms_messenger_group8;

import java.io.InputStream;
import java.util.ArrayList;

import android.app.Activity;
import android.content.ContentResolver;
import android.content.ContentUris;
import android.content.Context;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.provider.ContactsContract;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageView;
//import android.widget.ImageView;
import android.widget.TextView;

/**
 * Custom Adapter for generating message list
 * @author Group 8
 *
 */
public class ContactsAdapter extends ArrayAdapter<MyMessage> implements Filterable {
    public static boolean searchingForContact = false;
	private final Context context;
    private ArrayList<MyMessage> data;
    private ArrayList<MyMessage> dataOrigin;
    private final int layoutResourceId;
    private Filter filter;

    public ContactsAdapter(Context context, int layoutResourceId, ArrayList<MyMessage> data) {
        super(context, layoutResourceId, data);
        this.context = context;
        this.data = data;
        this.layoutResourceId = layoutResourceId;
        this.dataOrigin = new ArrayList<MyMessage>();
        this.dataOrigin.addAll(data);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View message = convertView;
        ViewHolder holder = null;

        if(message == null)
        {
        	if(layoutResourceId == R.layout.conversation_layout) {
        		//inflate a conversation layout if the layout requires it
                LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                message = inflater.inflate(R.layout.conversation_layout, parent, false);

                holder = new ViewHolder();
                holder.icon = (ImageView)message.findViewById(R.id.iconConv);
                holder.contactName = (TextView)message.findViewById(R.id.contactNameConv);
                holder.messageDate = (TextView)message.findViewById(R.id.messageDateConv);
                holder.messageBody = (TextView)message.findViewById(R.id.messageBodyConv);
                holder.isDraft = (TextView)message.findViewById(R.id.isDraftConv);

                message.setTag(holder);
        	}
        	else {
        		//inflate a message layout if the Id isn't the conversation layout Id
                LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                message = inflater.inflate(R.layout.message_layout, parent, false);

                holder = new ViewHolder();
                holder.icon = (ImageView)message.findViewById(R.id.icon);
                holder.contactName = (TextView)message.findViewById(R.id.contactName);
                holder.messageDate = (TextView)message.findViewById(R.id.messageDate);
                holder.messageBody = (TextView)message.findViewById(R.id.messageBody);
                holder.isDraft = (TextView)message.findViewById(R.id.isDraft);

                message.setTag(holder);
        	}
        }
        else
        {
            holder = (ViewHolder)message.getTag();
        }

        MyMessage contact = data.get(position);
	    
        holder.icon.setImageBitmap(contact.getIcon());
        holder.contactName.setText(contact.getContactName());
        holder.messageDate.setText(contact.getMessageDate());
        holder.messageBody.setText(contact.getMessageBody());
        holder.isDraft.setText(contact.isDraft());

        return message;
    }
    
    @Override
    /**
     * Returns message filter
     */
    public Filter getFilter()
    {
        if (filter == null)
            filter = new MessageFilter();

        return filter;
    }
    
    /**
     * Message filter to grab conversations
     * @author Group8
     *
     */
    private class MessageFilter extends Filter
    {
        @Override
        protected FilterResults performFiltering(CharSequence constraint)
        {   
            FilterResults results = new FilterResults();
            String prefix = constraint.toString().toLowerCase();

            if (prefix == null || prefix.length() == 0)
            {
                ArrayList<MyMessage> list = new ArrayList<MyMessage>(dataOrigin);
                results.values = list;
                results.count = list.size();
            }
            else
            {
                final ArrayList<MyMessage> list = new ArrayList<MyMessage>(dataOrigin);
                final ArrayList<MyMessage> newList = new ArrayList<MyMessage>();
                int count = list.size();

                // if we are searching for contacts, search contact name instead
                if(searchingForContact == true) {
                	for (int i=0; i<count; i++)
                    {
                        final MyMessage message = list.get(i);
                        final String value = message.getContactName().toLowerCase();
                        
                        if (value.startsWith(prefix))
                        {
                        	newList.add(message);
                        }
                    }
                    results.values = newList;
                    results.count = newList.size();
                }
                else {
                	for (int i=0; i<count; i++)
                    {
                        final MyMessage message = list.get(i);
                        final String value = message.getMessageBody().toLowerCase();
                     
                        if (value.startsWith(prefix))
                        {
                        	newList.add(message);
                        }
                    }
                    results.values = newList;
                    results.count = newList.size();
                }
                
                
            }
            // reset searching for contacts to be false for the next search
            searchingForContact = false;
            return results;
        }

        @SuppressWarnings("unchecked")
        @Override
        protected void publishResults(CharSequence constraint, FilterResults results) {
            data = (ArrayList<MyMessage>)results.values;

            clear();
            int count = data.size();
            for (int i=0; i<count; i++)
            {
            	MyMessage message = (MyMessage)data.get(i);
                add(message);
            }
        }
    }
    
    /**
     * Placeholder for values
     * @author Group8
     *
     */
    static class ViewHolder
    {
    	ImageView icon;
        TextView contactName;
        TextView messageDate;
        TextView messageBody;
        TextView isDraft;
    }

}
