package Lesson5;

public class Tunnel extends Stage {
    private static int count;
    public Tunnel() {
        this.length = 80;
        this.description = "Тоннель " + length + " метров";
    }
    @Override
    public void go(Car c) {
        try {
            try {
                System.out.println(c.getName() + " готовится к этапу(ждет): " + description);
                Car.getSmp().acquire();
                System.out.println(c.getName() + " начал этап: " + description);
                count++;
                System.out.printf("[DEBUG] В тонеле %d участников %n ",count);
                Thread.sleep(length / c.getSpeed() * 1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                System.out.println(c.getName() + " закончил этап: " + description);
                count--;
                Car.getSmp().release();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

