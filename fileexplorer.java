package com.example.fileexplorer;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import android.app.AlertDialog;
import android.app.ListActivity;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

public class fileexplorer extends ListActivity {
	
	private List<String> item = null;
	private List<String> path = null;
	private String root="/";
	private TextView myPath;
	File f = new File(root);
	File[] files = f.listFiles();
	Button name;
	Button size;
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        name=(Button)findViewById(R.id.sortbyname);
        size=(Button)findViewById(R.id.sortbysize);
        name.setOnClickListener(sort_name);
        size.setOnClickListener(sort_size);
        myPath = (TextView)findViewById(R.id.path);
        getDir(root);
    }
    
    private void getDir(String dirPath)
    {
    	myPath.setText("Location: " + dirPath);
    	
    	item = new ArrayList<String>();
    	path = new ArrayList<String>();
    	
    	
    	
    	if(!dirPath.equals(root))
    	{

    		item.add(root);
    		path.add(root);
    		
    		item.add("../");
    		path.add(f.getParent());
            
    	}
    	
    	for(int i=0; i < files.length; i++)
    	{
    			File file = files[i];
    			path.add(file.getPath());
    			if(file.isDirectory())
    				item.add(file.getName() + "/");
    			else
    				item.add(file.getName());
    	}

    	
    	
    	ArrayAdapter<String> fileList =
    		new ArrayAdapter<String>(this, R.layout.row, item);
    	setListAdapter(fileList);
    	
    }
    
    View.OnClickListener sort_name = new View.OnClickListener() {
		
		@Override
		public void onClick(View v) {
			int i,j;
			for(i=0;i<files.length;i++)
			{
				for(j=i;j<files.length;j++)
				{
					if(strcmp(files[j],files[j+1]>0))
					{
						file t=files[j];
						file[j]=file[j+1];
						file[j+1]=t;
					}
				}
			}
				
			}
			
		};
		
		View.OnClickListener sort_size= new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				int i,j;
				for(i=0;i<files.length;i++)
				{
					for(j=0;j<files.length;j++)
					{
						if(files[j].size()>files[j+1].size())
						{
							file t= files[j];
							file[j]=file[j+1];
							file[j+1]=t;
						}
					}
				}
				
			}
		};
	@Override
	protected void onListItemClick(ListView l, View v, int position, long id) {
		
		File file = new File(path.get(position));
		
		if (file.isDirectory())
		{
			if(file.canRead())
				getDir(path.get(position));
			else
			{
				new AlertDialog.Builder(this)
				.setIcon(R.drawable.ic_launcher)
				.setTitle("[" + file.getName() + "] folder can't be read!")
				.setPositiveButton("OK", 
						new DialogInterface.OnClickListener() {
							
							@Override
							public void onClick(DialogInterface dialog, int which) {
								
							}
						}).show();
			}
		}
		else
		{
			new AlertDialog.Builder(this)
				.setIcon(R.drawable.ic_launcher)
				.setTitle("[" + file.getName() + "]")
				.setPositiveButton("OK", 
						new DialogInterface.OnClickListener() {
							
							public void onClick(DialogInterface dialog, int which) {
								
							}
				}.show();
		
	}

						
		
	
	
	