package th.ac.pim.picshare;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;

public class MainActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		
		
//		Frame1();
		
//		((Button) findViewById(R.id.imageButton1)).setOnClickListener(new View.OnClickListener() {			
//			@Override
//			public void onClick(View v){
//				Intent gotoFrame1 = new Intent(getApplicationContext(), Frame1.class);
//				startActivity(gotoFrame1);
//			}
//		});
//		((Button) findViewById(R.id.imageButton2)).setOnClickListener(new View.OnClickListener() {			
//			@Override
//			public void onClick(View v) {
//				Intent goFrame2 = new Intent(getApplicationContext(), Frame2.class);
//				startActivity(goFrame2);;
//			}
//		});
	}
	
	
//	public void gotoFrame1(View v){
//		Intent gotoFrame1 = new Intent(this, Frame1.class);
//		startActivity(gotoFrame1);
//	}
//	public void gotoFrame2(View v){
//		Intent gotoFrame2 = new Intent(this, Frame2.class);
//		startActivity(gotoFrame2);
//	}
	
	
//	public void Frame1()
//	{
//		ImageButton a =  (ImageButton)findViewById(R.id.imageButton1);
//		a.setOnClickListener(new OnClickListener() {
//			
//			@Override
//			public void onClick(View v) {
//				
//				Intent gotoFrame1 = new Intent(MainActivity.this,Frame1.class);
//				startActivity(gotoFrame1);
//			}
//		});
//		
//	}
//	
	
	public void onClickFrame1Btn (View v) {
		Intent frame1 = new Intent(this, Frame1.class);
 	    startActivity(frame1);	 
	}
	public void onClickFrame2Btn (View v) {
		Intent frame2 = new Intent(this, Frame2.class);
 	    startActivity(frame2);	 
	}
	

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

}
