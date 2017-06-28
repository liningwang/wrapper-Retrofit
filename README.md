# wrapper-Retrofit
封装了retrofit代码
因为只用retrofit，初始化retrofit，然后enqueue，传回调还是有点小麻烦，所以封装了一些。
1.创建service接口
public interface TestService {

    @GET("Camera/servlet/MessageServlet")
    Call<String> getMessage();

    @Streaming
    @GET
    Call<ResponseBody> downApk(@Url String url);

    @Multipart
    @POST("UpdateServlet/servlet/Upload")
    Call<String> uploadFile(@Part MultipartBody.Part file);
}
2.在fragment中进行网络请求
/**
 * A simple {@link Fragment} subclass.
 */
public class TestFragment extends Fragment implements HttpCallBack{
    WelcomMess welcomMess;

    public TestFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_blank, container, false);
        Button bt = (Button) v.findViewById(R.id.mess);
        bt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                BaseHttp http = new BaseHttp(TestFragment.this);
//下载文件
//                http.downloadFile(TestService.class,new File("/mnt/sdcard/update.apk"),"downApk",
//                        "http://192.168.0.2:8080/DetectApkServlet/update.apk", ResponseBody.class);
//上传文件
                http.uploadFile(TestService.class,new File("/mnt/sdcard/update.apk"),"uploadFile",
                        ResponseBody.class,"pictrue");
            }
        });
        return v;
    }

//服务端响应成功，o表示response.body,method用来区分那次请求
    @Override
    public void onSuccess(Object o,String method) {
        Toast.makeText(getActivity(),(String)o,Toast.LENGTH_LONG)
                .show();
    }

    @Override
    public void onFailed(Object o,String method) {

    }

    @Override
    public void onProgress(int percent,String method) {

        Log.d("wang","percent is " + percent + " method " + method);
    }
}

详细的运行我的例子吧！
