package Server;

import java.net.InetAddress;
import java.util.Vector;

public class YunServerVector {  // server registry database
    private Vector<String> mIdVector = new Vector<>(3);
    private Vector<InetAddress> mIPVector = new Vector<>(3);
    private Vector<Integer> mPortVector = new Vector<>(3);

    public String getmId(int index) {
        return mIdVector.elementAt(index);
    }

    public InetAddress getmIP(int index) {
        return mIPVector.elementAt(index);
    }

    public Integer getmPort(int index) {
        return mPortVector.elementAt(index);
    }

    void putVectorElement(String message, InetAddress getmIP, int getmPort) {
        mIdVector.addElement(message);
        mIPVector.addElement(getmIP);
        mPortVector.addElement(getmPort);
    }

    void removeVectorElement(int index) {
        mIdVector.remove(index);
        mIPVector.remove(index);
        mPortVector.remove(index);
    }

    String viewTable() {
        String table = "********** REGISTER SERVER TABLE **********\n" +
                "* IP Address\tPort Num\tID\t\t\n";
        for(int i=0; i<mIPVector.size(); i++){
            table += "> " + mIPVector.elementAt(i) + "\t" + mPortVector.elementAt(i) + "\t\t" + mIdVector.elementAt(i) + "\n";
        }
        table += "*******************************************";
        return table;
    }

    int containID(String id){
        int result = -1;
        for(int i=0; i<mIdVector.size(); i++){
            if(mIdVector.elementAt(i).equals(id)){
                result = i;
            }
        }
        return result;
    }
    int containIP(InetAddress ip){
        int result = -1;
        for(int i=0; i<mIPVector.size(); i++){
            if(mIPVector.elementAt(i).equals(ip)){
                result = i;
            }
        }
        return result;
    }
}
