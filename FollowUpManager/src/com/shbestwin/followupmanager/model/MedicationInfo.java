package com.shbestwin.followupmanager.model;

import android.os.Parcel;
import android.os.Parcelable;

public class MedicationInfo implements Parcelable {
	private String pharmacology;// 药理学

	private String indications;// 适用症

	private String contraindications;// 禁忌症

	private String adverseReactions;// 不良反应

	private String notes;// 注意事项

	private String interaction;// 相互作用

	public MedicationInfo() {
	}

	public MedicationInfo(Parcel source) {
		pharmacology = source.readString();
		indications = source.readString();
		contraindications = source.readString();
		adverseReactions = source.readString();
		notes = source.readString();
		interaction = source.readString();

	}

	@Override
	public int describeContents() {
		return 0;
	}

	@Override
	public void writeToParcel(Parcel dest, int flags) {
		dest.writeString(pharmacology);
		dest.writeString(indications);
		dest.writeString(contraindications);
		dest.writeString(adverseReactions);
		dest.writeString(notes);
		dest.writeString(interaction);

	}

	public static final Parcelable.Creator<MedicationInfo> CREATOR = new Creator<MedicationInfo>() {

		@Override
		public MedicationInfo createFromParcel(Parcel source) {
			return new MedicationInfo(source);
		}

		@Override
		public MedicationInfo[] newArray(int size) {
			return new MedicationInfo[size];
		}
	};

	public String getPharmacology() {
		return pharmacology;
	}

	public void setPharmacology(String pharmacology) {
		this.pharmacology = pharmacology;
	}

	public String getIndications() {
		return indications;
	}

	public void setIndications(String indications) {
		this.indications = indications;
	}

	public String getContraindications() {
		return contraindications;
	}

	public void setContraindications(String contraindications) {
		this.contraindications = contraindications;
	}

	public String getAdverseReactions() {
		return adverseReactions;
	}

	public void setAdverseReactions(String adverseReactions) {
		this.adverseReactions = adverseReactions;
	}

	public String getNotes() {
		return notes;
	}

	public void setNotes(String notes) {
		this.notes = notes;
	}

	public String getInteraction() {
		return interaction;
	}

	public void setInteraction(String interaction) {
		this.interaction = interaction;
	}

	@Override
	public String toString() {
		return "MedicationInfo [pharmacology=" + pharmacology + ", indications=" + indications + ", contraindications=" + contraindications + ", adverseReactions=" + adverseReactions + ", notes=" + notes + ", interaction=" + interaction
				+ "]";
	}

}
