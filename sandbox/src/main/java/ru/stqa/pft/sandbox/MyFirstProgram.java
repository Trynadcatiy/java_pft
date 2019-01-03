package ru.stqa.pft.sandbox;

public class MyFirstProgram {
    public static void main(String[] args) {
        hello("world");
        hello("user");

        Square s = new Square(5);
        System.out.println("Площадь квадрата со стороной " + s.l + " = " + s.area());

        Rectangle r = new Rectangle(4, 6);
        System.out.println("Площадб прямоугольника со сторонами " + r.a + " и " + r.b + " = " + r.area());

        Point p1 = new Point(1, 2);
        Point p2 = new Point(3, 5);
        System.out.println("Расстояние от точки p1 до p2 = " + p1.distance(p2));
        System.out.println("Расстояние от точки p2 до p1 = " + p2.distance(p1));
        System.out.println("Расстояние от точки p1 до p1 = " + p1.distance(p1));
        System.out.println("Расстояние от точки p2 до p2 = " + p2.distance(p2));
    }

    public static void hello(String somebody) {
        System.out.println("Hello, " + somebody + "!");
    }
}