package Mummi;

import Mummi.Enums.Color;
import Mummi.Enums.Mood;

public class Artifact{
        private String name;
        private Color color = null;
        private Mood emotion = null;

        private String action = "";
        private int size = 0;

        public Artifact(String name, Color color, Mood emotion){
                this.name = name; this.color = color; this.emotion = emotion;
                ActionInit();
        }
        public Artifact(String name){
            this.name = name;
            ActionInit();
        }

        public String getName() {return name;}
        public Color getColor() {return color;}
        public Mood getEmotion() {return emotion;}
        public int getSize() {
                return size;
        }

        public void setName(String name){this.name = name;}
        public void setColor(Color color) {
                this.color = color;
        }
        public void setEmotion(Mood emotion) {
                this.emotion = emotion;
        }
        public void setSize(int size) {
                this.size = size;
        }

        @Override
        public String toString() {
                String res = "";
                if (color!=null) res+=" "+color;
                if (emotion!=null) res+=" "+emotion;
                if (!res.equals("")) res+=" ";
                res+=name+action;

                return res;
        }
        @Override
        public boolean equals(Object obj) {
                if (!(obj instanceof Artifact)) return false;
                if (!name.equals(((Artifact)obj).name)) return false;
                if (!name.equals(((Artifact)obj).emotion.toString())) return false;
                if (!name.equals(((Artifact)obj).color.toString())) return false;
                return true;
        }
        @Override
        public int hashCode() {
                int res = 17;
                int s = 37;
                res = s*res + name.hashCode();
                res = s*res + ((color==null)?0:color.hashCode());
                res = s*res +((emotion==null)?0:emotion.hashCode());
                return res;
        }


        public String getAction() {
                return action;
        }

        protected void ActionInit(){
                Functional_Action tempoAction = null;
                if (this.name.equals("Dust")){
                        tempoAction = (args) -> action = ", rolling over the floor";
                }

                if (this.name.equals("Big Shell")){
                        tempoAction = (args) -> action = ", lying on its side";
                }
                if (this.name.equals("Doors")){
                        tempoAction = (args) -> action = ", clapping";
                }
                if (tempoAction != null) tempoAction.performAction();
        }
}
