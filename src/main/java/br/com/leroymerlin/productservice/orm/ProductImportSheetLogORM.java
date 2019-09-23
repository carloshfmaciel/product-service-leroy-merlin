package br.com.leroymerlin.productservice.orm;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "PRODUCT_IMPORT_SHEET_LOG")
public class ProductImportSheetLogORM implements Serializable {

	private static final long serialVersionUID = -6036711932585075429L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "ID")
	private Long id;

	@Column(name = "FILE_NAME")
	private String fileName;

	@Column(name = "START_PROCESS_DATE")
	private Date startProcessDate;

	@Column(name = "END_PROCESS_DATE")
	private Date endProcessDate;

	@Column(name = "STATUS")
	private Integer status;

	@Column(name = "MESSAGE_ERROR")
	private String messageError;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	public Date getStartProcessDate() {
		return startProcessDate;
	}

	public void setStartProcessDate(Date startProcessDate) {
		this.startProcessDate = startProcessDate;
	}

	public Date getEndProcessDate() {
		return endProcessDate;
	}

	public void setEndProcessDate(Date endProcessDate) {
		this.endProcessDate = endProcessDate;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public String getMessageError() {
		return messageError;
	}

	public void setMessageError(String messageError) {
		this.messageError = messageError;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((endProcessDate == null) ? 0 : endProcessDate.hashCode());
		result = prime * result + ((fileName == null) ? 0 : fileName.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((messageError == null) ? 0 : messageError.hashCode());
		result = prime * result + ((startProcessDate == null) ? 0 : startProcessDate.hashCode());
		result = prime * result + ((status == null) ? 0 : status.hashCode());
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
		ProductImportSheetLogORM other = (ProductImportSheetLogORM) obj;
		if (endProcessDate == null) {
			if (other.endProcessDate != null)
				return false;
		} else if (!endProcessDate.equals(other.endProcessDate))
			return false;
		if (fileName == null) {
			if (other.fileName != null)
				return false;
		} else if (!fileName.equals(other.fileName))
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (messageError == null) {
			if (other.messageError != null)
				return false;
		} else if (!messageError.equals(other.messageError))
			return false;
		if (startProcessDate == null) {
			if (other.startProcessDate != null)
				return false;
		} else if (!startProcessDate.equals(other.startProcessDate))
			return false;
		if (status == null) {
			if (other.status != null)
				return false;
		} else if (!status.equals(other.status))
			return false;
		return true;
	}

}
