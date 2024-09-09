abstract class Shape{ 
    abstract double calculateArea();
    abstract double calculatePerimeter();}
    class Circle extends Shape{ 
        private double radius;
        public Circle(double radius){  
        this.radius = radius;
 } 
 @Override
   double calculateArea(){  
    return Math.PI * radius * radius;
 } @Override
  double calculatePerimeter(){ 
  return Math.PI*2*radius;
 }
}
  class Rectangle extends Shape{
   private double width;
   private double height; public Rectangle(double width, double height){
   this.width = width;  this.height = height;
 } @Override
    double calculateArea(){  
    return width * height;
 } 
 @Override
 double calculatePerimeter(){  
    return 2*width*height;
 }
}
class Triangle extends Shape{ 
 private double a;
 private double b; 
 private double c;
 public Triangle(double a, double b, double c){  
    this.a = a;
    this.b = b;  this.c = c;
 } @Override
    double calculateArea(){  
    double p = (a+b+c)/2;
    return Math.sqrt(p*(p-a)*(p-b)*(p-c)); }
 @Override 
  double calculatePerimeter(){
  return a+b+c; 
  }
}
 public class Practise4A {
  public static void main(String[] args){  
    Circle circle = new Circle(7);
  System.out.println("Area: " + circle.calculateArea());  
  System.out.println("Perimeter: " + circle.calculatePerimeter());
  Rectangle rectangle = new Rectangle(3, 7);  
  System.out.println("Area: " + rectangle.calculateArea());
  System.out.println("Perimeter: " + rectangle.calculatePerimeter());  
  Triangle triangle = new Triangle(3, 4, 5);
  System.out.println("Area: " + triangle.calculateArea());  
  System.out.println("Perimeter: " + triangle.calculatePerimeter());
 }
}