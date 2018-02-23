//package com.julyyu.arsenal.ui.fragment;
//
//import android.os.Bundle;
//import android.support.annotation.Nullable;
//import android.view.View;
//import android.webkit.WebSettings;
//import android.webkit.WebView;
//
//import com.julyyu.arsenal.R;
//import com.julyyu.uilibrary.fragment.BaseFragment;
//import com.tencent.sonic.sdk.SonicSession;
//
//import butterknife.BindView;
//
///**
// * Created by julyyu on 2017/8/25.
// */
//
//public class WebViewFragment extends BaseFragment {
//    public final static String PARAM_URL = "param_url";
//
//    public final static String PARAM_MODE = "param_mode";
//    @BindView(R.id.webview)
//    WebView webview;
//    String url = "http://192.168.17.62:8000";
//
//    private SonicSession sonicSession;
//
//    @Override
//    protected int getLayout() {
//        return R.layout.view_webview;
//    }
//
//    @Override
//    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
//        super.onViewCreated(view, savedInstanceState);
////        // init sonic engine if necessary, or maybe u can do this when application created
////        if (!SonicEngine.isGetInstanceAllowed()) {
////            SonicEngine.createInstance(new HostSonicRuntime(getContext()), new SonicConfig.Builder().build());
////        }
////
////        SonicSessionClientImpl sonicSessionClient = null;
////
////        // if it's sonic mode , startup sonic session at first time
////        SonicSessionConfig.Builder sessionConfigBuilder = new SonicSessionConfig.Builder();
////        // create sonic session and run sonic flow
////        sonicSession = SonicEngine.getInstance().createSession(url, sessionConfigBuilder.build());
////        if (null != sonicSession) {
////            if (sonicSessionClient != null) {
////                sonicSessionClient.bindWebView(webview);
////                sonicSessionClient.clientReady();
////            }
////            sonicSession.bindClient(sonicSessionClient);
////        } else {
////            // this only happen when a same sonic session is already running,
////            // u can comment following code to feedback for default mode to
////            throw new UnknownError("create session fail!");
////        }
//
//        // start init flow ... in the real world, the init flow may cost a long time as startup
//        // runtime、init configs....
//
//        // init webview
////        webview.setWebViewClient(new WebViewClient() {
////            @Override
////            public void onPageFinished(WebView view, String url) {
////                super.onPageFinished(view, url);
////                if (sonicSession != null) {
////                    sonicSession.getSessionClient().pageFinish(url);
////                }
////            }
////
////            @TargetApi(21)
////            @Override
////            public WebResourceResponse shouldInterceptRequest(WebView view, WebResourceRequest request) {
////                return shouldInterceptRequest(view, request.getUrl().toString());
////            }
////
////            @Override
////            public WebResourceResponse shouldInterceptRequest(WebView view, String url) {
////                if (sonicSession != null) {
////                    return (WebResourceResponse) sonicSession.getSessionClient().requestResource(url);
////                }
////                return null;
////            }
////        });
//
//        WebSettings webSettings = webview.getSettings();
//
//        // add java script interface
//        // note:if api level if lower than 17(android 4.2), addJavascriptInterface has security
//        // issue, please use x5 or see https://developer.android.com/reference/android/webkit/
//        // WebView.html#addJavascriptInterface(java.lang.Object, java.lang.String)
//        webSettings.setJavaScriptEnabled(true);
//        webview.removeJavascriptInterface("searchBoxJavaBridge_");
//
//        // init webview settings
//        webSettings.setAllowContentAccess(true);
//        webSettings.setDatabaseEnabled(true);
//        webSettings.setDomStorageEnabled(true);
//        webSettings.setAppCacheEnabled(true);
//        webSettings.setSavePassword(false);
//        webSettings.setSaveFormData(false);
//        webSettings.setUseWideViewPort(true);
//        webSettings.setLoadWithOverviewMode(true);
//        webview.loadUrl(url);
//
//        // webview is ready now, just tell session client to bind
////        if (sonicSessionClient != null) {
////            sonicSessionClient.bindWebView(webview);
////            sonicSessionClient.clientReady();
////        } else { // default mode
////            webview.loadUrl(url);
////        }
//
//    }
//
//
//
//
//
//
//}
