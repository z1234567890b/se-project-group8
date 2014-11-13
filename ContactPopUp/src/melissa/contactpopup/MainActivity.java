package melissa.contactpopup;

import android.app.Activity; 
import android.content.ContentValues;
import android.content.Intent;
import android.os.Bundle; 
import android.provider.Contacts.Intents;
import android.provider.ContactsContract;
import android.view.View; 
import android.view.View.OnClickListener; 
import android.view.ViewGroup.LayoutParams; 
import android.widget.Button; 
import android.widget.LinearLayout; 
import android.widget.PopupMenu;
import android.widget.PopupWindow; 
import android.widget.TextView;
import android.view.Menu;
import android.view.MenuItem;

public class MainActivity extends Activity implements OnClickListener{

	LinearLayout layoutOfPopup; 
	PopupWindow popupMessage; 
	PopupMenu popupMenu;
	Button popupButton, insidePopupButton1, insidePopupButton2; 
	TextView popupText; 
	
	@Override public void onCreate(Bundle savedInstanceState) { 
		super.onCreate(savedInstanceState); setContentView(R.layout.activity_main); 
		
		init();
		popupInit();
		}
	
	public void init() {
		
		popupButton = (Button) findViewById(R.id.popupbutton);
		popupMenu = new PopupMenu(this, popupButton);
		popupMenu.inflate(R.menu.main);
		//popupText = new TextView(this); 
		//insidePopupButton1 = new Button (this);
		//insidePopupButton2 = new Button (this);
		//layoutOfPopup = new LinearLayout(this); 
		//insidePopupButton1.setText("Yes"); 
		//insidePopupButton2.setText("No");
		//popupText.setText("Would you like to save the number to your contact list?"); 
		//popupText.setPadding(0, 0, 0, 20); 
		//layoutOfPopup.setOrientation(1); 
		//layoutOfPopup.addView(popupText); 
		//layoutOfPopup.addView(insidePopupButton1);
		//layoutOfPopup.addView(insidePopupButton2);
		}
	
	@SuppressWarnings("deprecation")
	public void popupInit() { 
		
		//Use this to make a button visible/invisible based on if there is something typed in? Can
		//show menu underneath button when selected... This would solve the need to have a button 
		//to trigger the menu problem I'm currently having
		
		//Set button invisible originally (can do in graphical xml view) and set visible when unknown
		//number is typed in?
		
		//popupButton.setVisibility(4); //Invisible = constant value 4
									  //Visible = constant value 0
	
		popupButton.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				popupMenu.show();
				
			}
		}); 
		
		popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
						
			@Override
			public boolean onMenuItemClick(MenuItem item) {
				switch(item.getItemId())
				{
					case R.id.menu_yes:
						Intent intent = new Intent(Intent.ACTION_INSERT, ContactsContract.Contacts.CONTENT_URI);
						//Below is where you would put the textbox name for the box you're using
						//and tell it to get the text
						intent.putExtra(Intents.Insert.PHONE, "469-396-8024");
						startActivity(intent);
						break;
					case R.id.menu_no:
						popupMenu.dismiss();
						break;
				}
				return true;
			}
		});
		//insidePopupButton1.setOnClickListener(this); 
		//insidePopupButton2.setOnClickListener(this);
		//popupMessage = new PopupWindow(layoutOfPopup,LayoutParams.FILL_PARENT, LayoutParams.WRAP_CONTENT); 
		//popupMessage.setContentView(layoutOfPopup); 
		}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		
	} 
	
	/*@SuppressWarnings({ "deprecation" })
	@Override public void onClick(View v) {
		if (v.getId() == R.id.popupbutton) { 
			popupMessage.showAsDropDown(popupButton, 0, 0); 
			} 
		else if()
		{
			//if yes is selected
			//dismiss popup
			//call intent and pass it the number that was typed
			Intent intent = new Intent(Intent.ACTION_INSERT, ContactsContract.Contacts.CONTENT_URI);
			//Below is where you would put the textbox name for the box you're using
			//and tell it to get the text
			intent.putExtra(Intents.Insert.PHONE, "469-396-8024");
			startActivity(intent);
		}
		else { 
			// if no selected, just dismiss popup			
			popupMessage.dismiss();
		}
	}*/

	
}
