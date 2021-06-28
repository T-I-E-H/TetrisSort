package tetris;

public class FigureField {
    private int Lenght;
    private int Width;
    private int FilledByFigure;
    private int FilledCells;
    private int[][] Fig;
    private int[][] Cells;
    private int count=0;
    public FigureField(int N, int M){
        Lenght=N;
        Width=M;
        FilledCells=M*N;
        Cells = new int[Lenght][Width];
    }

    public void ShowField(){
        for(int i = 0; i<Lenght;i++){
            for(int k = 0; k<Width;k++){
                System.out.printf("%3d ", Cells[i][k]);
                if(k==Width-1) System.out.println();
            }
        }
        System.out.print('\n');
    }
    public boolean PutIn(String Type, int y, int x){
        
        int Begin=0;
        Figure figure = new Figure(Type,++count);
        FilledByFigure=figure.GetFilled();
        FilledCells-=FilledByFigure;;
        figure.Spin(Type.charAt(Type.length()-1));
        Fig=figure.GetFig();
        while(Fig[Fig.length-1][Begin]==0){
            Begin++;
        }
        for(int i = y; i>y-Fig.length;i--){
            for(int k = x; k<x+Fig[0].length;k++){
                if(i==y && k==x) k+=Begin;
                try{
                    if(Cells[i][k-Begin]!=0 && Fig[i-y+Fig.length-1][k-x]!=0){
                        this.CtrlZ(y);
                        return false;
                    }
                    else if (Fig[i-y+Fig.length-1][k-x]!=0){
                        Cells[i][k-Begin]=Fig[i-y+Fig.length-1][k-x];
                    }
                }
                catch(ArrayIndexOutOfBoundsException e){
                    this.CtrlZ(y);
                    return false;
                }
            }
        }
        return true;
    }
    
    private void CtrlZ(int i1){
        for(int i = i1;i>=0;i--){
            for(int k = 0; k<Width; k++){
                if(Cells[i][k]==count){ 
                    Cells[i][k]=0;
                }
            }
        }
        count--;
        FilledCells+=FilledByFigure;
    }
    public boolean BrainsOn(){
        return Brains(Lenght-1,0);
    }
    private boolean Brains(int i1, int k1){
        if(FilledCells==0) {
            return true;
        }
        int Angle = 0;
        int type;
        int mirror = 0;
        for(int i = i1; i>=0;i--){
            for(int k = k1; k<Width;k++){
                if (Cells[i][k]==0){
                    type=0;
                    while(type!=4){
                        if(this.PutIn(((type+1)+""+mirror+""+Angle),i,k)){
                            this.Brains(i, k);
                            if(FilledCells==0) {
                                return true;
                            }
                            this.CtrlZ(i);
                        }
                        if(Angle!=1&&type==0)Angle++;
                        else if(Angle!=3&&type!=0)Angle++;
                        else if (mirror==0&&type!=1){
                            mirror++;
                            Angle=0;
                        }
                        else if (type!=2){
                            type++;
                            mirror=0;
                            Angle=0;
                        }
                        else{
                            return false;
                        }
                    }
                }
                k1=0;
            }
        }
        return false;
    }
}
