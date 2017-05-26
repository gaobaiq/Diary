package com.gbq.diary.widget.toolbar;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.res.TypedArray;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.gbq.diary.R;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * 类说明：自定义导航栏
 * Author: Kuzan
 * Date: 2017/5/26 9:48.
 */
public class BaseBar extends Toolbar {
    @Bind(R.id.tv_title)
    TextView tvTitle;
    @Bind(R.id.img_title)
    ImageView mImgTitle;
    @Bind(R.id.ib_left)
    ImageButton ibLeft;
    @Bind(R.id.btn_left)
    TextView btnLeft;
    @Bind(R.id.ib_right)
    ImageButton ibRight;
    @Bind(R.id.btn_right)
    TextView btnRight;
    @Bind(R.id.btn_search)
    ImageView mBtnSearch;
    @Bind(R.id.et_search)
    EditText mEtSearch;
    @Bind(R.id.tv_search)
    TextView mTvSearch;
    @Bind(R.id.layout_search)
    LinearLayout mLayoutSearch;

    private View rootView;

    private Context mContext;

    private boolean isLeft;
    private int leftIcon;
    private boolean isLeftText;
    private int leftText;
    private boolean isRight;
    private int rightIcon;
    private boolean isRightText;
    private int rightText;
    private boolean isTitleText;
    private int titleText;
    private boolean isTitleIcon;
    private int titleIcon;
    private boolean isSearch;

    private String searchValue;

    private OnBtnListener mListener;

    public BaseBar(Context context) {
        this(context, null);
    }

    public BaseBar(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public BaseBar(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        this.mContext = context;
        setCustomAttributes(attrs);
        initLayout(context);
    }

    private void initLayout(Context context) {
        rootView = LayoutInflater.from(context).inflate(R.layout.toolbar_base, null);
        addView(rootView, new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, getResources().getDimensionPixelOffset(R.dimen.toolbar_height)));
        ButterKnife.bind(this, rootView);

        if (isTitleText) {
            tvTitle.setVisibility(VISIBLE);
            tvTitle.setText(titleText);
        } else {
            tvTitle.setVisibility(GONE);
        }

        if (isTitleIcon) {
            mImgTitle.setVisibility(VISIBLE);
            mImgTitle.setImageResource(titleIcon);
        } else {
            mImgTitle.setVisibility(GONE);
        }

        if (isLeft) {
            ibLeft.setVisibility(VISIBLE);
            ibLeft.setImageResource(leftIcon);
        } else {
            ibLeft.setVisibility(GONE);
        }

        if (isLeftText) {
            btnLeft.setVisibility(VISIBLE);
            btnLeft.setText(leftText);
        } else {
            btnLeft.setVisibility(GONE);
        }

        if (isRight) {
            ibRight.setVisibility(VISIBLE);
            ibRight.setImageResource(rightIcon);
        } else {
            ibRight.setVisibility(GONE);
        }

        if (isRightText) {
            btnRight.setVisibility(VISIBLE);
            btnRight.setText(rightText);
        } else {
            btnRight.setVisibility(GONE);
        }

        if (isSearch) {
            mLayoutSearch.setVisibility(VISIBLE);
        } else {
            mLayoutSearch.setVisibility(GONE);
        }

        mEtSearch.setOnEditorActionListener(new TextView.OnEditorActionListener() {

            @Override
            public boolean onEditorAction(TextView arg0, int arg1, KeyEvent arg2) {
                // TODO Auto-generated method stub
                if (arg1 == EditorInfo.IME_ACTION_SEARCH ||
                        (arg2 != null && arg2.getKeyCode() == KeyEvent.KEYCODE_ENTER)) {
                    onSearchClick();
                }
                return false;
            }
        });
    }

    /**
     * 获取自定义属性值
     */
    @SuppressLint("Recycle")
    private void setCustomAttributes(AttributeSet attrs) {
        TypedArray typedArray = this.mContext.obtainStyledAttributes(attrs, R.styleable.baseBar);
        isLeft = typedArray.getBoolean(R.styleable.baseBar_is_left, false);
        leftIcon = typedArray.getResourceId(R.styleable.baseBar_left_icon, 0);

        isLeftText = typedArray.getBoolean(R.styleable.baseBar_is_left_text, false);
        leftText = typedArray.getResourceId(R.styleable.baseBar_left_text, R.string.save);

        isRight = typedArray.getBoolean(R.styleable.baseBar_is_right, false);
        rightIcon = typedArray.getResourceId(R.styleable.baseBar_right_icon, 0);

        isRightText = typedArray.getBoolean(R.styleable.baseBar_is_right_text, false);
        rightText = typedArray.getResourceId(R.styleable.baseBar_right_text, R.string.save);

        isTitleText = typedArray.getBoolean(R.styleable.baseBar_is_title_text, false);
        titleText = typedArray.getResourceId(R.styleable.baseBar_title_text, R.string.app_name);

        isTitleIcon = typedArray.getBoolean(R.styleable.baseBar_is_title_icon, false);
        titleIcon = typedArray.getResourceId(R.styleable.baseBar_title_icon, 0);

        isSearch = typedArray.getBoolean(R.styleable.baseBar_is_search, false);
    }

    public void setTitle(String title) {
        tvTitle.setText(title);
    }

    public void setTitle(int titleRes) {
        tvTitle.setText(titleRes);
    }

    public void setTitleIcon(boolean isTitle, int res) {
        if (mImgTitle != null) {
            if (isTitle) {
                mImgTitle.setVisibility(VISIBLE);
                mImgTitle.setImageResource(res);
            } else {
                mImgTitle.setVisibility(INVISIBLE);
            }
        }
    }

    public void setLeftIcon(boolean isLeft, int res) {
        if (ibLeft != null) {
            if (isLeft) {
                ibLeft.setVisibility(VISIBLE);
                ibLeft.setImageResource(res);
            } else {
                ibLeft.setVisibility(INVISIBLE);
            }
        }
    }

    public void setRightIcon(boolean isRight, int res) {
        if (ibRight != null) {
            if (isRight) {
                ibRight.setVisibility(VISIBLE);
                ibRight.setImageResource(res);
            } else {
                ibRight.setVisibility(INVISIBLE);
            }
        }
    }

    public void setRightIconPadding(int left, int top, int right, int bottom) {
        ibRight.setPadding(left,top,right,bottom);
    }

    public void setLeftText(String res) {
        if (isLeftText) {
            btnLeft.setVisibility(VISIBLE);
            btnLeft.setText(res);
        }
    }

    public void setLeftText(int res) {
        if (isLeftText) {
            btnLeft.setVisibility(VISIBLE);
            btnLeft.setText(res);
        }
    }


    public void setRightText(int res) {
        if (isRightText) {
            btnRight.setVisibility(VISIBLE);
            btnRight.setText(res);
        }
    }

    public void setSearchIcon(int res) {
        if (mBtnSearch != null) {
            mBtnSearch.setImageResource(res);
        }
    }

    public void setSearchBg(int res) {
        if (mLayoutSearch != null) {
            mLayoutSearch.setBackgroundResource(res);
        }
    }

    public void setSearchHint(int res) {
        if (mEtSearch != null) {
            mEtSearch.setHint(res);
        }
    }

    public void setSearchValue(String value) {
        if (mEtSearch != null) {
            searchValue = value;
            mEtSearch.setText(value);
        }
    }

    public void setSearchEditable(boolean isEdit) {
        if (isEdit) {
            if (mEtSearch != null) {
                mEtSearch.setVisibility(VISIBLE);
            }
            if (mTvSearch != null) {
                mTvSearch.setVisibility(GONE);
            }
        } else {
            if (mEtSearch != null) {
                mEtSearch.setVisibility(GONE);
            }
            if (mTvSearch != null) {
                mTvSearch.setVisibility(VISIBLE);
            }
        }
    }

    public void setBackgroundAlpha(int alpha) {
        this.rootView.getBackground().setAlpha(alpha);
    }

    public void setBackgroundColor(int color) {
        this.rootView.setBackgroundColor(color);
    }

    public void hideTitleText() {
        tvTitle.setVisibility(INVISIBLE);
    }

    public void showTitleText() {
        tvTitle.setVisibility(VISIBLE);
    }

    public void hideTitleIcon() {
        mImgTitle.setVisibility(INVISIBLE);
    }

    public void showTitleIcon() {
        mImgTitle.setVisibility(VISIBLE);
    }

    public void hideLeft() {
        ibLeft.setVisibility(INVISIBLE);
    }

    public void showLeft() {
        ibLeft.setVisibility(VISIBLE);
    }

    public void hideRightText() {
        btnRight.setVisibility(INVISIBLE);
    }

    public void showRightText() {
        btnRight.setVisibility(VISIBLE);
    }

    public void hideRight() {
        ibRight.setVisibility(INVISIBLE);
    }

    public void showRight() {
        ibRight.setVisibility(VISIBLE);
    }

    public String getSearchValue() {
        searchValue = mEtSearch.getText().toString();
        return TextUtils.isEmpty(searchValue) ? "" : searchValue;
    }

    @OnClick(R.id.ib_left)
    void backClick() {
        hideInput();
        if (mListener != null) {
            mListener.onBtnClick(ibLeft);
        } else {
            ((Activity) mContext).finish();
        }
    }

    @OnClick(R.id.btn_right)
    void rightClick() {
        if (mListener != null) {
            mListener.onBtnClick(btnRight);
        }
    }

    @OnClick(R.id.ib_right)
    void rightImageClick() {
        if (mListener != null) {
            mListener.onBtnClick(ibRight);
        }
    }


    @OnClick(R.id.btn_search)
    public void onSearchClick() {
        if (mListener != null) {
            mListener.onBtnClick(mBtnSearch);
        }

        // 隐藏软键盘
        if (!TextUtils.isEmpty(searchValue)) {
            hideInput();
        }
    }


    public void hideInput() {
        InputMethodManager imm = (InputMethodManager) mContext.getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.hideSoftInputFromWindow(mEtSearch.getWindowToken(), 0);
    }


    public interface OnBtnListener {
        void onBtnClick(View view);
    }

    public void setOnBtnListener(OnBtnListener listener) {
        this.mListener = listener;
    }
}
