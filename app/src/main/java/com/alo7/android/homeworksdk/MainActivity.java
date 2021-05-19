package com.alo7.android.homeworksdk;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;

import com.alo7.android.homework.Alo7Error;
import com.alo7.android.homework.Alo7HomeworkSDK;
import com.alo7.android.homework.ResultCallback;
import com.alo7.android.homework.utils.LogUtils;
import com.alo7.android.homework.utils.ToastUtils;
import com.alo7.android.homework.web.CommonConstants;
import com.alo7.android.homework.web.UniversalWebViewActivity;
import com.alo7.android.homeworksdk.loading.LoadingUtils;
import com.trello.rxlifecycle3.components.support.RxAppCompatActivity;

public class MainActivity extends RxAppCompatActivity {
    private final LoadingUtils dialogUtils = new LoadingUtils();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    /**
     * 老师布置作业
     */
    public void assignHomework(View view) {
        String token = ((EditText) findViewById(R.id.et_teacher_token)).getText().toString().trim();
        if (TextUtils.isEmpty(token)) {
            ToastUtils.showToast("请输入teacher token");
            return;
        }
        dialogUtils.showLoading(this);
        Alo7HomeworkSDK.assignHomework(this, token, new ResultCallback() {
            @Override
            public void onSuccess() {
                dialogUtils.dismiss();
            }

            @Override
            public void onError(Alo7Error error) {
                dialogUtils.dismiss();
                LogUtils.e("Main", error.getErrorMessage());
                ToastUtils.showToast("错误码：" + error.getErrorCode() + " 错误信息：" + error.getErrorMessage());
            }
        });
    }

    /**
     * 老师检查作业
     */
    public void checkHomework(View view) {
        String token = ((EditText) findViewById(R.id.et_teacher_token)).getText().toString().trim();
        String homeworkId = ((EditText) findViewById(R.id.et_homework_id)).getText().toString().trim();
        if (TextUtils.isEmpty(token)) {
            ToastUtils.showToast("请输入teacher token");
            return;
        }
        if (TextUtils.isEmpty(homeworkId)) {
            ToastUtils.showToast("请输入homework id");
            return;
        }
        dialogUtils.showLoading(this);
        Alo7HomeworkSDK.checkHomework(this, homeworkId, token, new ResultCallback() {
            @Override
            public void onSuccess() {
                dialogUtils.dismiss();
            }

            @Override
            public void onError(Alo7Error error) {
                dialogUtils.dismiss();
                LogUtils.e("Main", error.getErrorMessage());
                ToastUtils.showToast("错误码：" + error.getErrorCode() + " 错误信息：" + error.getErrorMessage());
            }
        });
    }

    /**
     * 学生查看作业
     */
    public void doHomework(View view) {
        String token = ((EditText) findViewById(R.id.et_student_token)).getText().toString().trim();
        String homeworkId = ((EditText) findViewById(R.id.et_homework_id)).getText().toString().trim();
        if (TextUtils.isEmpty(token)) {
            ToastUtils.showToast("请输入student token");
            return;
        }
        if (TextUtils.isEmpty(homeworkId)) {
            ToastUtils.showToast("请输入homework id");
            return;
        }
        dialogUtils.showLoading(this);
        Alo7HomeworkSDK.showHomework(this, homeworkId, token, new ResultCallback() {
            @Override
            public void onSuccess() {
                dialogUtils.dismiss();
            }

            @Override
            public void onError(Alo7Error error) {
                dialogUtils.dismiss();
                LogUtils.e("Main", error.getErrorMessage());
                ToastUtils.showToast("错误码：" + error.getErrorCode() + " 错误信息：" + error.getErrorMessage());
            }
        });
    }
}