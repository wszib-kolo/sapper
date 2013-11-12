package sapper;

public class Field {

        private boolean mine;
        private int increaseNearMinesNumber = 0;
        private boolean cover = true;

        public boolean isMine() {
                return mine;
        }
        public void increaseNearMinesNumber(){
        	increaseNearMinesNumber++;
        }
        public boolean isCover() {
                return cover;
        }

        public void setCover(boolean cover) {
                this.cover = cover;
        }

        public void setMine(boolean mine) {
                this.mine = mine;
        }

        public int getNearMinesNumber() {
                return increaseNearMinesNumber;
        }

        public void setNearMinesNumber(int nearMinesNumber) {
                this.increaseNearMinesNumber = nearMinesNumber;
        }

}