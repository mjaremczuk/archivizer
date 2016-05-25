package pl.reveo.presentation.view.toolkit;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.util.AttributeSet;
import android.widget.FrameLayout;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;

/**
 * Container view.
 */
public class ContainerView extends FrameLayout {

	private static final String KEY_BACK_STACK_ELEMENTS = "containerView.backStack";
	private static final String KEY_BACK_STACK_BUNDLES = "containerView.bundles";

	/**
	 * String that keeps name of currently visible element.
	 */
	private static final String KEY_CURRENT_ELEMENT = "containerView.currentElement";

	/**
	 * Bundle that keeps state of currently visible element.
	 */
	private static final String KEY_CURRENT_BUNDLE = "containerView.currentBundle";

	private ArrayList<String> backStack = new ArrayList<>();
	private ArrayList<Bundle> bundles = new ArrayList<>();

	private Screen currentElement;
	private Activity activity;

	public ContainerView(Context context) {
		super(context);
	}

	public ContainerView(Context context, AttributeSet attrs) {
		super(context, attrs);
	}

	public ContainerView(Context context, AttributeSet attrs, int defStyleAttr) {
		super(context, attrs, defStyleAttr);
	}

	public void onCreateLifecycle(Bundle savedInstanceState) {
		if (savedInstanceState != null) {
			backStack = savedInstanceState.getStringArrayList(KEY_BACK_STACK_ELEMENTS);
			bundles = savedInstanceState.getParcelableArrayList(KEY_BACK_STACK_BUNDLES);
			currentElement = createElementViewByName(savedInstanceState.getString(KEY_CURRENT_ELEMENT));
			currentElement.setBundle(savedInstanceState.getBundle(KEY_CURRENT_BUNDLE));
			currentElement.onCreateLifecycle(getContext(), this);
		}
	}

	public void addElement(Screen screen) {
		if (currentElement != null) {
			Bundle bundle = new Bundle();
			currentElement.onDestroyLifecycle(this);
			currentElement.onSaveStateLifeCycle(bundle);
			backStack.add(currentElement.getClass().getName());
			bundles.add(bundle);
		}
		currentElement = screen;
		screen.onCreateLifecycle(getContext(), this);
	}

	public void onSaveInstanceStateLifecycle(Bundle outState) {
		outState.putStringArrayList(KEY_BACK_STACK_ELEMENTS, backStack);
		outState.putParcelableArrayList(KEY_BACK_STACK_BUNDLES, bundles);
		outState.putString(KEY_CURRENT_ELEMENT, currentElement.getClass().getName());
		Bundle bundle = new Bundle();
		currentElement.onSaveStateLifeCycle(bundle);
		outState.putBundle(KEY_CURRENT_BUNDLE, bundle);
	}

	public void onBackPressed() {
		if (backStack.size() > 0) {
			currentElement.onDestroyLifecycle(this);
			currentElement = createElementViewByName(backStack.remove(backStack.size() - 1));
			currentElement.setBundle(bundles.remove(bundles.size() - 1));
			currentElement.onCreateLifecycle(getContext(), this);
		}
	}

	public boolean isOneElementOnBackStack() {
		return backStack.size() == 0;
	}

	public void setActivity(Activity activity) {
		this.activity = activity;
	}

	public Activity getActivity() {
		return activity;
	}

	private Screen createElementViewByName(String name) {
		Class<?> c = null;
		try {
			c = Class.forName(name);
			Constructor<?> cons = c.getConstructor();
			Screen object = (Screen) cons.newInstance();
			return object;
		}
		catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		catch (InvocationTargetException e) {
			e.printStackTrace();
		}
		catch (NoSuchMethodException e) {
			e.printStackTrace();
		}
		catch (InstantiationException e) {
			e.printStackTrace();
		}
		catch (IllegalAccessException e) {
			e.printStackTrace();
		}

		return null;
	}
}
