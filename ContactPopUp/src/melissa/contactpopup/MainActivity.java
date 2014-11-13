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
import android.widget.PopupWindow; 
import android.widget.TextView;
import android.view.Menu;
import android.view.MenuItem;

public class MainActivity extends Activity implements OnClickListener{

	LinearLayout layoutOfPopup; 
	PopupWindow popupMessage; 
	Button popupButton, insidePopupButton1, insidePopupButton2; 
	TextView popupText; 
	
	@Override public void onCreate(Bundle savedInstanceState) { 
		super.onCreate(savedInstanceState); setContentView(R.layout.activity_main); 
		init(); popupInit();
		}
	
	public void init() {
		popupButton = (Button) findViewById(R.id.popupbutton); 
		popupText = new TextView(this); 
		insidePopupButton1 = new Button (this);
		insidePopupButton2 = new Button (this);
		layoutOfPopup = new LinearLayout(this); 
		insidePopupButton1.setText("Yes"); 
		insidePopupButton2.setText("No");
		popupText.setText("Would you like to save the number to your contact list?"); 
		popupText.setPadding(0, 0, 0, 20); 
		layoutOfPopup.setOrientation(1); 
		layoutOfPopup.addView(popupText); 
		layoutOfPopup.addView(insidePopupButton1);
		layoutOfPopup.addView(insidePopupButton2);
		}
	
	@SuppressWarnings("deprecation")
	public void popupInit() { 
		popupButton.setOnClickListener(this); 
		insidePopupButton1.setOnClickListener(this); 
		insidePopupButton2.setOnClickListener(this);
		popupMessage = new PopupWindow(layoutOfPopup,LayoutParams.FILL_PARENT, LayoutParams.WRAP_CONTENT); 
		popupMessage.setContentView(layoutOfPopup); } 
	
	@SuppressWarnings({ "unused", "deprecation" })
	@Override public void onClick(View v) {
		if (v.getId() == R.id.popupbutton) { 
			popupMessage.showAsDropDown(popupButton, 0, 0); 
			} 
		else { 
			//if yes is selected
			//dismiss popup
			//call intent and pass it the number that was typed
			Intent intent = new Intent(Intent.ACTION_INSERT, ContactsContract.Contacts.CONTENT_URI);
			//Below is where you would put the textbox name for the box you're using
			//and tell it to get the text
			intent.putExtra(Intents.Insert.PHONE, "469-396-8024");
			startActivity(intent);
			
			popupMessage.dismiss();
		}
	}

	
}
