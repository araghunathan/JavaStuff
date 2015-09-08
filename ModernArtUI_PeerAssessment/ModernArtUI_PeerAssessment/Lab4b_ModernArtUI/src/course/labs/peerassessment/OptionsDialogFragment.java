package course.labs.peerassessment;
import android.app.DialogFragment;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;


public class OptionsDialogFragment extends DialogFragment {

	private static final String TAG = "OPTIONS_DIALOG_FRAGMENT";

	public OptionsDialogFragment(){
		// Empty constructor required for DialogFragment
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
							 Bundle savedInstanceState) {
		View view = inflater.inflate(R.layout.dialog_fragment, container);
		final Button cancelButton = (Button)view.findViewById(R.id.cancel);
		cancelButton.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View view) {
				dismiss();
			}
		});
		final Button visitMomaButton = (Button)view.findViewById(R.id.visitMoma);
		visitMomaButton.setOnClickListener(new View.OnClickListener(){
			@Override
			public void onClick(View view) {
				final Intent baseIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("http://www.moma.org"));
				startActivity(baseIntent);
			}
		});
		getDialog().setTitle(getResources().getString(R.string.dialog_heading));
		return view;
	}
}

