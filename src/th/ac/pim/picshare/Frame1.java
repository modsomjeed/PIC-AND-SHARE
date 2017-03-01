package th.ac.pim.picshare;

import java.io.File;



//import th.ac.pim.moblieproject.R;
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
import android.support.v4.app.NavUtils;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ImageButton;
import android.widget.Toast;

public class Frame1 extends Activity {
	private Uri mImageCaptureUri;
	private ImageView mImageView;	
	private ImageView mImageView2;
	int count =0;
    int button =0;
    Bitmap bitmap1;
    Bitmap bitmap2;
	
	private static final int PICK_FROM_CAMERA = 1;
	private static final int PICK_FROM_FILE = 2;
	
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        
        setContentView(R.layout.activity_frame1);
        
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
//	                if(mImageView)
	                startActivityForResult(Intent.createChooser(intent, "Complete action using"), PICK_FROM_FILE);
				}
			}
		} );
		
		final AlertDialog dialog = builder.create();
		
		mImageView = (ImageView) findViewById(R.id.imageButton1);
		
		mImageView.setOnClickListener(new View.OnClickListener() {			
			@Override
			public void onClick(View v) {
				dialog.show();
				button= 1;
				
			}
		});
		
		final AlertDialog dialog2 = builder.create();
		
		mImageView2 = (ImageView) findViewById(R.id.imageButton2);
		
		mImageView2.setOnClickListener(new View.OnClickListener() {			
			@Override
			public void onClick(View v) {
				dialog2.show();
				button= 2;
			}
		});
    }
    
    @Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
	    if (resultCode != RESULT_OK) return;
	
		Bitmap bitmap 	= null;
		String path		= "";
	
		if (requestCode == PICK_FROM_FILE) {
			mImageCaptureUri = data.getData(); 
			path = getRealPathFromURI(mImageCaptureUri); //from Gallery 
		
			if (path == null)
				path = mImageCaptureUri.getPath(); //from File Manager
			
			if (path != null) 
				bitmap 	= BitmapFactory.decodeFile(path);
		} else {
			path	= mImageCaptureUri.getPath();
			bitmap  = BitmapFactory.decodeFile(path);
		}
		if(button ==1){	
		mImageView.setImageBitmap(bitmap);	
		count=1;
		//Toast.makeText(this,"1111111", 
         //       Toast.LENGTH_SHORT).show();
			bitmap1 =bitmap;
		}
		
		else if(button ==2){	
			mImageView2.setImageBitmap(bitmap);	
			count =0;
			bitmap2 =bitmap;
			}
		else{
			Toast.makeText(this,"err", 
	                Toast.LENGTH_SHORT).show();
			
			}
		
	}
	
	public String getRealPathFromURI(Uri contentUri) {
        String [] proj 		= {MediaStore.Images.Media.DATA};
        Cursor cursor 		= managedQuery( contentUri, proj, null, null,null);
        
        if (cursor == null) return null;
        
        int column_index 	= cursor.getColumnIndexOrThrow(MediaStore.Images.Media.DATA);
        
        cursor.moveToFirst();

        return cursor.getString(column_index);
	}
	
	
	public void share () {
		Intent share = new Intent(Intent.ACTION_SEND);
		share.setType("image/jpeg");
		share.putExtra(Intent.EXTRA_STREAM, mImageCaptureUri);
		startActivity(Intent.createChooser(share, "Share Image"));
	}
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		switch (item.getItemId()) {
		case android.R.id.home:
			//
			NavUtils.navigateUpFromSameTask(this);
			return true;
			
		case R.id.menuitem_share:
			share();
			return true;
		}
		return super.onOptionsItemSelected(item);
	}
}