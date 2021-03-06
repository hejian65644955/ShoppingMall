package com.atguigu.shoppingmall;

import android.os.Bundle;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.widget.FrameLayout;
import android.widget.RadioGroup;

import com.atguigu.shoppingmall.base.BaseFragment;
import com.atguigu.shoppingmall.community.CommunityFragment;
import com.atguigu.shoppingmall.home.fragment.HomeFragment;
import com.atguigu.shoppingmall.shopping.ShoppingFragment;
import com.atguigu.shoppingmall.type.TypeFragment;
import com.atguigu.shoppingmall.user.UserFragment;

import java.util.ArrayList;
import java.util.List;

import butterknife.ButterKnife;
import butterknife.InjectView;

public class MainActivity extends AppCompatActivity {

    @InjectView(R.id.fl_main)
    FrameLayout flMain;
    @InjectView(R.id.rg_main)
    RadioGroup rgMain;

    List<BaseFragment> fragments = new ArrayList<>();

    private  BaseFragment tempFragment;

    //记录当前位置
    private int position;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.inject(this);


        initFragment();

        initListener();
    }

    private void initListener() {
        rgMain.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                switch (checkedId){
                    case R.id.rb_home:
                        position =0;
                        break;
                    case R.id.rb_type:
                        position =1;
                        break;
                    case R.id.rb_community:
                        position =2;
                        break;
                    case R.id.rb_cart:
                        position =3;
                        break;
                    case R.id.rb_user:
                        position =4;
                        break;
                }
                BaseFragment currentFragment = fragments.get(position);
                switchFragment(currentFragment);
            }

        });
        rgMain.check(R.id.rb_home);
    }

    private void switchFragment(BaseFragment currentFragment) {
        if(currentFragment != tempFragment){
            FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
            if(currentFragment.isAdded()){
                if(tempFragment!=null){
                    ft.hide(tempFragment);
                }
                ft.show(currentFragment);
            }else{

                if(tempFragment!=null){
                    ft.hide(tempFragment);
                }
                ft.add(R.id.fl_main,currentFragment);
            }
            ft.commit();
            tempFragment =currentFragment;
        }
    }

    private void initFragment() {
        fragments.add(new HomeFragment());
        fragments.add(new TypeFragment());
        fragments.add(new CommunityFragment());
        fragments.add(new ShoppingFragment());
        fragments.add(new UserFragment());
    }
}
