package bacit.web.bacit_web;

import bacit.web.bacit_models.FileModel;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.OutputStream;

import static org.junit.jupiter.api.Assertions.*;

class FileDownloadServletTest {

    @org.junit.jupiter.api.Test
    void doGet() throws Exception {
        //Arrange
        FileDownloadServletFake unitUnderTest = new FileDownloadServletFake();
        //Act
        unitUnderTest.doGet(null,null);
        //Assert
        assertEquals("Filename.ext",unitUnderTest.getReceivedModel().getName());
    }
}

class FileDownloadServletFake extends FileDownloadServlet{

    private  FileModel receivedModel;
    public FileModel getReceivedModel() {
        return receivedModel;
    }

    private void setReceivedModel(FileModel receivedModel) {
        this.receivedModel = receivedModel;
    }
    @Override
    protected void WriteFileResult(HttpServletResponse response, FileModel model) throws IOException
    {
      setReceivedModel(model);
    }

    @Override
    protected FileModel GetFile(int id) throws Exception {
        return new FileModel("Filename.ext",new byte[1], "application/octet-stream");
    }

    @Override
    protected String getQueryStringParameter(HttpServletRequest request, String parameter) {
        return "1";
    }
}