package tetris;

public class Figure {
    private int[][] Figure;
    private int FilledBy;
    public Figure(String Type,int c){
        //Здесь заполняются фигурки, используемые для заполнения поля. c -  
        //заполняемая клета, 0, соответственно, - пустая клетка
        switch(Type.charAt(0)){
            case '1': 
                Figure=new int[][]{
                    {c,c,0},
                    {0,c,c}
                };
                break;
            case '2':
                Figure=new int[][]{
                    {c,0},
                    {c,0},
                    {c,c}
                };
                break;
            case '3':
                Figure=new int[][]{
                    {c,c,c},
                    {0,c,0}
                }; 
                break;
        } 
        for(int i=0;i<Figure.length;i++){
            for(int j=0;j<Figure[0].length;j++){
                if (Figure[i][j]==c) FilledBy++;
            }
        }
        switch(Type.charAt(1)){
            case '0':
                break;
            case '1':
                this.Mirror();
                break;
        }
    }
    public void ShowFigure(){
    for(int i = 0; i<Figure.length;i++){
            for(int k = 0; k<Figure[0].length;k++){
                System.out.print(Figure[i][k]);
                if(k!=Figure[0].length-1) System.out.print(" ");
                else System.out.print('\n');
            }
        }
    System.out.print('\n');
    }
    public int GetFilled(){
        return FilledBy;
    }
    
    private void Mirror(){
            int temp;
            for(int i = 0; i<Figure.length;i++){
                temp=Figure[i][0];
                Figure[i][0]=Figure[i][Figure[0].length-1];
                Figure[i][Figure[0].length-1]=temp;
            } 
    }
    private void Rotate(){
        int[][] Trans = new int[Figure[0].length][Figure.length];
        for (int i = 0; i < Figure[0].length; i++) {
            for (int j = 0; j < Figure.length; j++) {
                Trans[i][j] = Figure[j][i];
            }
        }
        Figure=Trans; 
        
        this.Mirror();
        
    }
    public int[][] GetFig(){
        return Figure;
    }
    public void Spin(char Angle){
        switch(Angle){
            case '3':
                this.Rotate();
            case '2':
                this.Rotate();
            case '1':
                this.Rotate();
            case '0':
                break;
        }
    }
}
