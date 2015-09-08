package course.labs.peerassessment;

import android.app.Activity;
import android.app.FragmentManager;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.SeekBar;

import java.util.Random;

public class ModernArtUIActivity extends Activity {

	private static final String TAG = "Lab-ModernArtUI";

	// IDs for menu items
	private static final int MENU_MORE_INFO = Menu.FIRST;


	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		int[] nonGrayWhiteViewResIds = {R.id.redRect, R.id.magentaRect, R.id.greenBlueRect, R.id.yellowRect};
		final View[] nonGrayViews = new View[nonGrayWhiteViewResIds.length];

		for(int i = 0; i < nonGrayWhiteViewResIds.length; i++){
			nonGrayViews[i] = findViewById(nonGrayWhiteViewResIds[i]);
		}

		final SeekBar seekBar = (SeekBar) findViewById(R.id.color_change_seekbar);
		seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
			@Override
			public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
				Log.i(TAG, "Progress Changed");
				//gradual changing of color for each of the non-gray views
				for (int j = 0; j < nonGrayViews.length; j++) {
					final int alphaColor = (i+j) % 255;
					final int color = ((i+j)*10) % 255;
					final int color2 = ((i+j)*20) % 255;
					final int color3 = ((i+j)*30) % 255;
					final int nextColor = Color.argb(alphaColor, color, color2, color3);
					nonGrayViews[j].setBackgroundColor(nextColor);
				}
			}

			@Override
			public void onStartTrackingTouch(SeekBar seekBar) {
				Log.i(TAG, "Started Tracking Touch");
			}

			@Override
			public void onStopTrackingTouch(SeekBar seekBar) {
				Log.i(TAG, "Stop Tracking Touch");
			}
		});
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		super.onCreateOptionsMenu(menu);
		menu.add(Menu.NONE, MENU_MORE_INFO, Menu.NONE, getResources().getString(R.string.more_info));
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		switch (item.getItemId()) {
			case MENU_MORE_INFO:
				FragmentManager fragmentManager = getFragmentManager();
				OptionsDialogFragment dialogFragment = new OptionsDialogFragment();
				dialogFragment.show(fragmentManager, TAG);
				return true;
			default:
				return super.onOptionsItemSelected(item);
		}
	}
}