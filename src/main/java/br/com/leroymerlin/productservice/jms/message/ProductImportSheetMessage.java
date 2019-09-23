package br.com.leroymerlin.productservice.jms.message;

import java.io.Serializable;
import java.util.Arrays;

public class ProductImportSheetMessage implements Serializable {

	private static final long serialVersionUID = -4601533462989097404L;

	private Long processId;

	private String fileName;

	private byte[] fileContent;

	public Long getProcessId() {
		return processId;
	}

	public void setProcessId(Long processId) {
		this.processId = processId;
	}

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	public byte[] getFileContent() {
		return fileContent;
	}

	public void setFileContent(byte[] fileContent) {
		this.fileContent = fileContent;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + Arrays.hashCode(fileContent);
		result = prime * result + ((fileName == null) ? 0 : fileName.hashCode());
		result = prime * result + ((processId == null) ? 0 : processId.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		ProductImportSheetMessage other = (ProductImportSheetMessage) obj;
		if (!Arrays.equals(fileContent, other.fileContent))
			return false;
		if (fileName == null) {
			if (other.fileName != null)
				return false;
		} else if (!fileName.equals(other.fileName))
			return false;
		if (processId == null) {
			if (other.processId != null)
				return false;
		} else if (!processId.equals(other.processId))
			return false;
		return true;
	}

}
