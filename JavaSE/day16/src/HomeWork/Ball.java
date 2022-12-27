package HomeWork;


    interface Playable {
        void play();
    }

    interface Bounceable {
        void play();
    }

    interface Rollable extends Playable, Bounceable {
        Ball ball = new Ball("PingPang"); // 接口中只有全局常量
    }


    public class Ball implements Rollable {

        private String name;
        public String getName() {
            return name;
        }
        public Ball(String name) {
            this.name = name;
        }

        public void play() {
            //ball = new Ball("Football"); // 全局常量不允许再次赋值!!!
            System.out.println(ball.getName());
        }
    }

