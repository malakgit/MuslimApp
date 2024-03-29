package hamood.malak.muslimapp.MyUI.ui.main;

import android.content.Context;

import androidx.annotation.Nullable;
import androidx.annotation.StringRes;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import hamood.malak.muslimapp.R;


/**
 * A [FragmentPagerAdapter] that returns a fragment corresponding to
 * one of the sections/tabs/pages.
 */
public class SectionsPagerAdapter extends FragmentPagerAdapter {

    AllPostsFragment allPostsFragment;
    MyPostFragment myPostFragment;
    MapsFragment myFragment;

    @StringRes
    private static final int[] TAB_TITLES = new int[]{R.string.tab_text_1, R.string.tab_text_2};
    private final Context mContext;

    public SectionsPagerAdapter(Context context, FragmentManager fm) {
        super(fm);
        mContext = context;
    }

    @Override
    public Fragment getItem(int position) {
        if (position==0){
            if (allPostsFragment==null)
                allPostsFragment=new AllPostsFragment();
            return allPostsFragment;
        }
        if (position==1){
            if (myPostFragment==null)
                myPostFragment=new MyPostFragment();
            return myPostFragment;
        }
//        if (position==1){
//            if (myFragment==null)
//                myFragment=new MapsFragment();
//            return myFragment;
//        }
        return null;
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        return mContext.getResources().getString(TAB_TITLES[position]);
    }

    @Override
    public int getCount() {
        // Show 2 total pages.
        return 2;
    }
}