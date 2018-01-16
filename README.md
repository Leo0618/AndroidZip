# AndroidZip

Android端zip压缩与解压,目前暂时只做zip格式支持，基于Zip4j （[http://www.lingala.net/zip4j/](http://www.lingala.net/zip4j/)）进行扩展成工具类,支持对单个文件，多个文件以及文件夹进行压缩，对压缩文件解压到到指定目录，支持压缩解压使用密码。 详见使用案例：[MainActivity.java](https://github.com/Leo0618/AndroidZip/blob/master/app/src/main/java/com/leo618/androidzip/MainActivity.java)


----------


# 使用说明： #

- 依赖

		compile 'com.leo618:zip:0.0.1'


- 选择开启或者关闭日志打印
	
		ZipManager.debug(BuildConfig.DEBUG);

- 压缩
	
	    /**
	     * 压缩文件或者文件夹
	     *
	     * @param targetPath          被压缩的文件路径
	     * @param destinationFilePath 压缩后生成的文件路径
	     * @param callback            压缩进度回调
	     */
	    public static void zip(String targetPath, String destinationFilePath, IZipCallback callback)
	
	    /**
	     * 压缩文件或者文件夹
	     *
	     * @param targetPath          被压缩的文件路径
	     * @param destinationFilePath 压缩后生成的文件路径
	     * @param password            压缩加密 密码
	     * @param callback            压缩进度回调
	     */
	    public static void zip(String targetPath, String destinationFilePath, String password, IZipCallback callback)
		
	    /**
	     * 压缩多个文件
	     *
	     * @param list                被压缩的文件集合
	     * @param destinationFilePath 压缩后生成的文件路径
	     * @param callback            压缩进度回调
	     */
	    public static void zip(ArrayList<File> list, String destinationFilePath, IZipCallback callback)

	    /**
	     * 压缩多个文件
	     *
	     * @param list                被压缩的文件集合
	     * @param destinationFilePath 压缩后生成的文件路径
	     * @param password            压缩 密码
	     * @param callback            回调
	     */
	    public static void zip(ArrayList<File> list, String destinationFilePath, String password, final IZipCallback callback)

		
- 解压

	    /**
	     * 解压
	     *
	     * @param targetZipFilePath     待解压目标文件地址
	     * @param destinationFolderPath 解压后文件夹地址
	     * @param callback              回调
	     */
	    public static void unzip(String targetZipFilePath, String destinationFolderPath, IZipCallback callback)
	
	    /**
	     * 解压
	     *
	     * @param targetZipFilePath     待解压目标文件地址
	     * @param destinationFolderPath 解压后文件夹地址
	     * @param password              解压密码
	     * @param callback              回调
	     */
	    public static void unzip(String targetZipFilePath, String destinationFolderPath, String password, final IZipCallback callback)

- 回调接口

		public interface IZipCallback {
		    /**
		     * 开始
		     */
		    void onStart();
		
		    /**
		     * 进度回调
		     *
		     * @param percentDone 完成百分比
		     */
		    void onProgress(int percentDone);
		
		    /**
		     * 完成
		     *
		     * @param success 是否成功
		     */
		    void onFinish(boolean success);
		}

- 混淆

		-dontwarn com.leo618.zip.**
		-dontwarn net.lingala.zip4j.**
		-keep class com.leo618.zip.** { *; }
		-keep class net.lingala.zip4j.** { *; }