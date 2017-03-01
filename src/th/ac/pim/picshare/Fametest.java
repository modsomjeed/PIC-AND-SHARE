package th.ac.pim.picshare;

import java.io.File;



import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.view.View;
import android.widget.*;

public class Fametest extends Activity{
	private Uri mImageCaptureUri;
	private ImageView mImageView;	
	
	private static final int PICK_FROM_CAMERA = 1;
	private static final int PICK_FROM_FILE = 2;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_frametest);
		
		final String [] items			= new String [] {"Camera", "Gallery"};				
		ArrayAdapter<String> adapter	= new ArrayAdapter<String> (this, android.R.layout.select_dialog_item,items);
		AlertDialog.Builder builder		= new AlertDialog.Builder(this);
		
		builder.setTitle("ChooseImage");
		builder.setAdapter( adapter, new DialogInterface.OnClickListener() {
			public void onClick( DialogInterface dialog, int item ) {
				if (item == 0) {
					Intent intent 	 = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
					File file		 = new File(Environment.getExternalStorageDirectory(),
							   			"tmp_avatar_" + String.valueOf(System.currentTimeMillis()) + ".jpg");
					mImageCaptureUri = Uri.fromFile(file);

					try {			
						intent.putExtra(android.provider.MediaStore.EXTRA_OUTPUT, mImageCaptureUri);
						intent.putExtra("return-data", true);
						
						startActivityForResult(intent, PICK_FROM_CAMERA);
					} catch (Exception e) {
						e.printStackTrace();
					}			
					
					dialog.cancel();
				} else {
					Intent intent = new Intent();
					
	                intent.setType("image/*");
	                intent.setAction(Intent.ACTION_GET_CONTENT);
	                
	                startActivityForResult(Intent.createChooser(intent, "Complete action using"), PICK_FROM_FILE);
				}
			}
		} );
		
//		final AlertDialog dialog = builder.create();
//		mImageView = (ImageView) findViewById(R.id.iv_pic);
//		
//				
//		((ImageButton) findViewById(R.id.imageButton1)).setOnClickListener(new View.OnClickListener() {			
//			@Override
//			public void onClick(View v) {
//				final AlertDialog dialog = builder.create();
//				dialog.show();
//			}
//		});
//		
		
		
	}

}
