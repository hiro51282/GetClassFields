package JarExtract;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Enumeration;
import java.util.jar.JarEntry;
import java.util.jar.JarFile;

public class JarExtract {

    String jarFilePath = ""; // ファイルのパスを適切に指定してください
    String extractFolderPath = ""; // 出力フォルダのパスを適切に指定してください

    public JarExtract(String jarFilePath, String extractFolderPath) {
        this.jarFilePath = jarFilePath;
        this.extractFolderPath = extractFolderPath;

    }

    public JarExtract(String jarFilePath) {
        this.jarFilePath = jarFilePath;
        this.extractFolderPath = getTempFolderPath();

    }
    public String getExtractFolderPath(){
        return this.extractFolderPath;
    }

    public static String getTempFolderPath() {
        // システムプロパティ "java.io.tmpdir" を使用して一時フォルダのパスを取得
        String tempDir = System.getProperty("java.io.tmpdir");

        // パスの最後にファイルセパレータを追加
        if (!tempDir.endsWith(File.separator)) {
            tempDir = tempDir + File.separator;
        }

        return tempDir;
    }

    public void extractJar() throws IOException {
        try (JarFile jarFile = new JarFile(this.jarFilePath)) {
            Enumeration<JarEntry> entries = jarFile.entries();
            // 出力フォルダを作成
            File outputFolder = new File(this.extractFolderPath);
            outputFolder.mkdirs();

            // Jarファイル内のエントリを走査
            while (entries.hasMoreElements()) {
                JarEntry entry = entries.nextElement();
                if (!entry.isDirectory()) {
                    // 出力パスを生成
                    String entryName = entry.getName();
                    File outputFile = new File(outputFolder, entryName);

                    // 出力ファイルを作成
                    try (InputStream inputStream = jarFile.getInputStream(entry);
                            FileOutputStream outputStream = new FileOutputStream(outputFile)) {
                        byte[] buffer = new byte[1024];
                        int bytesRead;
                        while ((bytesRead = inputStream.read(buffer)) != -1) {
                            outputStream.write(buffer, 0, bytesRead);
                        }
                    }
                }
            }
        }
    }
}