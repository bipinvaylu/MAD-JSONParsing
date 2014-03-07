package com.madhunt.jsonparsing;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends Activity {

	TextView mTextView;
	Button mParseJson;

	//@formatter:off
	//JSON Validation : http://jsonlint.com/
	//Actual JSON String
	/*
	{
	    "object": {
	        "member1": "member1 value",
	        "member2": "member2 value",
	        "array": [
	            {
	                "element": "element value"
	            },
	            {
	                "element": "element value"
	            }
	        ]
	    }
	}
	*/
	//@formatter:on

	private static final String jsonString = "{\"object\": {\"member1\": \"member1 value\",\"member2\": \"member2 value\",\"array\": [{\"element\": \"element value\"},{\"element\": \"element value\"}]}}";

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		mParseJson = (Button) findViewById(R.id.parseJson);
		mTextView = (TextView) findViewById(R.id.textView);

		mParseJson.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				doJsonParsing();
			}
		});

	}

	public void doJsonParsing() {

		StringBuffer parsedStringBuffer = new StringBuffer();
		try {
			JSONObject jsonObject = new JSONObject(jsonString);

			JSONObject object = jsonObject.getJSONObject("object");
			String member1 = object.getString("member1");
			String member2 = object.getString("member2");

			parsedStringBuffer.append("Member1  = ").append(member1)
					.append("\n").append("Member2  = ").append(member2)
					.append("\n\n");

			JSONArray array = object.getJSONArray("array");

			parsedStringBuffer.append("Array length = ").append(array.length())
					.append("\n");

			for (int i = 0; i < array.length(); i++) {
				JSONObject element = array.getJSONObject(i);
				parsedStringBuffer.append("\t");
				parsedStringBuffer.append("Element[" + i + "] = ").append(
						element.getString("element"));
				parsedStringBuffer.append("\n");
			}
			mTextView.setText(parsedStringBuffer.toString());
			Log.d(MainActivity.class.getSimpleName(), "Parsed Data : "
					+ parsedStringBuffer.toString());
		} catch (JSONException e) {
			e.printStackTrace();
			mTextView.setText("Error = " + e.getMessage());
		}
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}
}
