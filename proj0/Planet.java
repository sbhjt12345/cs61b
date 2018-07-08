import java.lang.Math;

public class Planet {
	double xxPos, yyPos, xxVel, yyVel, mass;
	String imgFileName;
	
	public Planet(double xP, double yP, double xV,
              double yV, double m, String img){
		this.xxPos = xP;
		this.yyPos = yP;
		this.xxVel = xV;
		this.yyVel = yV;
		this.mass = m;
		this.imgFileName = img;

	}

	public Planet(Planet p){
		this.xxPos = p.xxPos;
		this.yyPos = p.yyPos;
		this.xxVel = p.xxVel;
		this.yyVel = p.yyVel;
		this.mass = p.mass;
		this.imgFileName = p.imgFileName;

	}

	public double calcDistance(Planet pp){
		double x_Dis = pp.xxPos - this.xxPos;
		double y_Dis = pp.yyPos - this.yyPos;
		return Math.sqrt(x_Dis*x_Dis + y_Dis*y_Dis);
	}

	public double calcForceExertedBy(Planet pp){
		double G = 6.67*10e-12;
		return this.mass * pp.mass * G / (calcDistance(pp) * calcDistance(pp));
	}

	public double calcForceExertedByX(Planet pp){
		double totalF = calcForceExertedBy(pp);
		return totalF * (pp.xxPos - this.xxPos) /calcDistance(pp);
	}

	public double calcForceExertedByY(Planet pp){
		double totalF = calcForceExertedBy(pp);
		return totalF * (pp.yyPos - this.yyPos) /calcDistance(pp);
	}

	public double calcNetForceExertedByX(Planet[] pps){
		double sum = 0.0;
		for (int i=0;i<pps.length;i++){
			if (pps[i].equals(this)) continue;
			sum += calcForceExertedByX(pps[i]);
		}
		return sum;
	}

	public double calcNetForceExertedByY(Planet[] pps){
		double res = 0.0;
		for (int i=0;i<pps.length;i++){
			if (pps[i].equals(this)) continue;
			res += calcForceExertedByY(pps[i]);
		}
		return res;
	}

	public void update(double dt, double fX, double fY){
		double aX = fX / this.mass;
		double aY = fY / this.mass;
		this.xxVel += dt * aX;
		this.yyVel += dt * aY;
		this.xxPos += dt * this.xxVel;
		this.yyPos += dt * this.yyVel; 
	}

















}
