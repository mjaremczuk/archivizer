package pl.reveo.presentation.view.activity;

import android.os.Bundle;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import pl.reveo.presentation.R;
import pl.reveo.presentation.view.screens.TemplatesScreen;
import pl.reveo.presentation.view.toolkit.ContainerView;

/**
 * Main application screen. This is the app entry point.
 */
public class MainActivity extends DefaultActivity {

	private Unbinder unbinder;

	@BindView(R.id.main_container_view)
	ContainerView container;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		unbinder = ButterKnife.bind(this);
		if (savedInstanceState == null) {
			container.addElement(new TemplatesScreen());
		}
		else {
			container.onCreateLifecycle(savedInstanceState);
		}
	}

	@Override
	protected void onSaveInstanceState(Bundle outState) {
		container.onSaveInstanceStateLifecycle(outState);
		super.onSaveInstanceState(outState);
	}

	@Override
	public void onBackPressed() {
		if (container.isOneElementOnBackStack()) {
			super.onBackPressed();
		}
		else {
			container.onBackPressed();
		}
	}

	@Override
	protected void onDestroy() {
		unbinder.unbind();
		super.onDestroy();
	}
}
