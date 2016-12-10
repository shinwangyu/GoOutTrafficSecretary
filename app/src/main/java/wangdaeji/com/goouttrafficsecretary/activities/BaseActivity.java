package wangdaeji.com.goouttrafficsecretary.activities;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.AppCompatCheckBox;
import android.support.v7.widget.SwitchCompat;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.webkit.WebView;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.GridView;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RelativeLayout;
import android.widget.ScrollView;
import android.widget.Spinner;
import android.widget.Switch;
import android.widget.TextView;

public abstract class BaseActivity extends AppCompatActivity
{
    /**
     * 현재 Activity UI.
     * Activity에서 layoutInit()함수에서
     * 이용 방법 : container = this; 로 레퍼런스 변수 할당함
     */
    public AppCompatActivity ac_Container = null;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        initLayout();
    }

    @Override
    protected void onStart()
    {
        super.onStart();
    }

    /**
     * 레이아웃 초기화
     */
    protected abstract void initLayout();

    protected View getView(final int _iViewID)
    {
        return ac_Container.findViewById(_iViewID);
    }

    protected RelativeLayout getRelativeLayout(final int _iViewID)
    {
        return (RelativeLayout)ac_Container.findViewById(_iViewID);
    }

    protected TextView getTextView(final int _iViewID)
    {
        return (TextView)ac_Container.findViewById(_iViewID);
    }

    protected ImageButton getImageButton(final int _iViewID)
    {
        return (ImageButton)ac_Container.findViewById(_iViewID);
    }

    protected ImageView getImageView(final int _iViewID)
    {
        return (ImageView)ac_Container.findViewById(_iViewID);
    }

    protected ScrollView getScrollView(final int _iViewID)
    {
        return (ScrollView)ac_Container.findViewById(_iViewID);
    }

    protected EditText getEditText(final int _iViewID)
    {
        return (EditText)ac_Container.findViewById(_iViewID);
    }

    protected Button getButton(final int _iViewID)
    {
        return (Button)ac_Container.findViewById(_iViewID);
    }
    protected ListView getListView(final int _iViewID)
    {
        return (ListView)ac_Container.findViewById(_iViewID);
    }

    protected LinearLayout getLinearLayout(final int _iViewID)
    {
        return (LinearLayout)ac_Container.findViewById(_iViewID);
    }

    protected CheckBox getCheckBox(final int _iViewID)
    {
        return (CheckBox)ac_Container.findViewById(_iViewID);
    }

    protected ViewPager getViewPager(final int _iViewID)
    {
        return (ViewPager)ac_Container.findViewById(_iViewID);
    }

    protected RadioGroup getRadioGroup(final int _iViewID)
    {
        return (RadioGroup)ac_Container.findViewById(_iViewID);
    }

    protected RadioButton getRadioButton(final int _iViewID)
    {
        return (RadioButton)ac_Container.findViewById(_iViewID);
    }

    protected WebView getWebView(final int _iViewID)
    {
        return (WebView)ac_Container.findViewById(_iViewID);
    }

    protected FrameLayout getFrameLayout(final int _iViewID)
    {
        return (FrameLayout)ac_Container.findViewById(_iViewID);
    }

    protected SwipeRefreshLayout getSwipeRefreshLayout(final int _iViewID)
    {
        return (SwipeRefreshLayout)ac_Container.findViewById(_iViewID);
    }

    protected GridView getGridView(final int _iViewID)
    {
        return (GridView)ac_Container.findViewById(_iViewID);
    }

    protected Switch getSwitch(final int _iViewID)
    {
        return (Switch)ac_Container.findViewById(_iViewID);
    }

    protected SwitchCompat getSwitchCompat(final int _iViewID)
    {
        return (SwitchCompat)ac_Container.findViewById(_iViewID);
    }

    protected AppCompatCheckBox getAppCompatCheckBox(final int _iViewID)
    {
        return (AppCompatCheckBox)ac_Container.findViewById(_iViewID);
    }

    protected Spinner getSpinner(final int _iViewID)
    {
        return (Spinner)ac_Container.findViewById(_iViewID);
    }

    protected Toolbar getToolbar(final int _iViewID)
    {
        return (Toolbar)ac_Container.findViewById(_iViewID);
    }

    protected FloatingActionButton getFloatingActionButton(final int _iViewID)
    {
        return (FloatingActionButton)ac_Container.findViewById(_iViewID);
    }

    protected DrawerLayout getDrawerLayout(final int _iViewID)
    {
        return (DrawerLayout)ac_Container.findViewById(_iViewID);
    }

    protected NavigationView getNavigationView(final int _iViewID)
    {
        return (NavigationView)ac_Container.findViewById(_iViewID);
    }








}