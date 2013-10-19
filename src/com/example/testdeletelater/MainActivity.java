package com.example.testdeletelater;

import java.math.BigDecimal;

import android.os.Bundle;
import android.app.Activity;
import android.text.Editable;
import android.text.Selection;
import android.text.Spannable;
import android.text.TextWatcher;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends Activity {

	private EditText watcherEditTextForCardIDField;
	private TextView mGetDestinationCardIDField;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.fund_transfer_activity);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	private void setActions() {

		watcherEditTextForCardIDField = new EditText(this);

		mGetDestinationCardIDField
				.addTextChangedListener(new CustomCardIDFieldWatcher(
						watcherEditTextForCardIDField));

	}	
	
	private void getReferences() {

			mGetDestinationCardIDField = (EditText) findViewById(R.id.editText3);
	}
	
	private class CustomCardIDFieldWatcher implements TextWatcher {

		EditText mEditText;

		public CustomCardIDFieldWatcher(EditText e) {
			mEditText = e;
		}

		public void beforeTextChanged(CharSequence s, int start, int count,
				int after) {
		}

		@Override
		public void onTextChanged(CharSequence s, int start, int before,
				int count) {

		}

		public void afterTextChanged(Editable s) {

			/*
			 * String phoneNumber = "1234567890"; StringBuilder sb = new
			 * StringBuilder(phoneNumber) .insert(4,"-") .insert(8,"-"); String
			 * output = sb.toString(); Log.i("app", String.valueOf(output));
			 */

			String fullText = mGetDestinationCardIDField.getText().toString()
					.trim();

			if (fullText.length() > 0) {

				// Removing all seperators and make it a raw string
				// If there is no sperator then the original string
				// shall be inserted to [0] index

				String[] splitText = fullText.split("-");
				String rawString = "";

				for (String sub : splitText) {

					rawString += sub;
				}

				// deducting one from the length as if the number is 1234 we
				// don't
				// want a seperator. We need it for 1234-5 or for 1234-5678-9
				// not for 1234 or 1234-5678

				BigDecimal numberLength = new BigDecimal(rawString.length() - 1);

				int noOfSeperator = numberLength.divide(new BigDecimal(4))
						.intValue();

				if (noOfSeperator > 0) {

					String newString = "";
					int i = 0;

					for (; i < noOfSeperator; i++) {

						newString += rawString.substring(i * 4, i * 4 + 4)
								+ "-";
					}

					// adding the tailing part of the rawString

					newString += rawString.substring(i * 4);

					mGetDestinationCardIDField.setText(newString);
					//Selection.setSelection((Spannable) mGetDestinationCardIDField.getText(), mGetDestinationCardIDField.getText().length());

				}
			}
		}

	}
	
}
