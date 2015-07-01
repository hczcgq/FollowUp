package com.shbestwin.followupmanager.model.device;

public class BodyComposition {

	private int age;// 年龄
	private String gendar;// 性别
	private int height;// 身高
	private int sportLevel;// 运动级别（1=非运动员，2-业余运动员，3=专业运动员）
	private float weight;// 体重
	private float fat;// 脂肪
	private float boneMass;// 骨量
	private float muscle;// 肌肉
	private int visceralFat;// 内脏脂肪
	private int bodyImpedance;// 人体阻抗值
	private float BMI;// 体质指数
	private float bodyWater;// 水分
	private int KCAL;// 基础代谢

	public BodyComposition() {
	}

	public BodyComposition(int age, String gendar, int height, int sportLevel, float weight, float fat, float boneMass, float muscle, int visceralFat, int bodyImpedance, float bMI, float bodyWater, int kCAL) {
		super();
		this.age = age;
		this.gendar = gendar;
		this.height = height;
		this.sportLevel = sportLevel;
		this.weight = weight;
		this.fat = fat;
		this.boneMass = boneMass;
		this.muscle = muscle;
		this.visceralFat = visceralFat;
		this.bodyImpedance = bodyImpedance;
		BMI = bMI;
		this.bodyWater = bodyWater;
		KCAL = kCAL;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public String getGendar() {
		return gendar;
	}

	public void setGendar(String gendar) {
		this.gendar = gendar;
	}

	public int getHeight() {
		return height;
	}

	public void setHeight(int height) {
		this.height = height;
	}

	public int getSportLevel() {
		return sportLevel;
	}

	public void setSportLevel(int sportLevel) {
		this.sportLevel = sportLevel;
	}

	public float getWeight() {
		return weight;
	}

	public void setWeight(float weight) {
		this.weight = weight;
	}

	public float getFat() {
		return fat;
	}

	public void setFat(float fat) {
		this.fat = fat;
	}

	public float getBoneMass() {
		return boneMass;
	}

	public void setBoneMass(float boneMass) {
		this.boneMass = boneMass;
	}

	public float getMuscle() {
		return muscle;
	}

	public void setMuscle(float muscle) {
		this.muscle = muscle;
	}

	public int getVisceralFat() {
		return visceralFat;
	}

	public void setVisceralFat(int visceralFat) {
		this.visceralFat = visceralFat;
	}

	public int getBodyImpedance() {
		return bodyImpedance;
	}

	public void setBodyImpedance(int bodyImpedance) {
		this.bodyImpedance = bodyImpedance;
	}

	public float getBMI() {
		return BMI;
	}

	public void setBMI(float bMI) {
		BMI = bMI;
	}

	public float getBodyWater() {
		return bodyWater;
	}

	public void setBodyWater(float bodyWater) {
		this.bodyWater = bodyWater;
	}

	public int getKCAL() {
		return KCAL;
	}

	public void setKCAL(int kCAL) {
		KCAL = kCAL;
	}

	@Override
	public String toString() {
		return "Weight [age=" + age + ", gendar=" + gendar + ", height=" + height + ", sportLevel=" + sportLevel + ", weight=" + weight + ", fat=" + fat + ", boneMass=" + boneMass + ", muscle=" + muscle + ", visceralFat=" + visceralFat
				+ ", bodyImpedance=" + bodyImpedance + ", BMI=" + BMI + ", bodyWater=" + bodyWater + ", KCAL=" + KCAL + "]";
	}

}
