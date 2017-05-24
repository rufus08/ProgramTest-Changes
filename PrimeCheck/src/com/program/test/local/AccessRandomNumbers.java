package com.program.test.local;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.List;

import com.program.test.remote.RmiInterface;

public class AccessRandomNumbers extends UnicastRemoteObject implements RmiInterface {

	private static final long serialVersionUID = 1L;
	
	private List<RandomNumCheck> randomNums;
	
	private static RmiInterface look_up;

	protected AccessRandomNumbers(List<RandomNumCheck> randomNums) throws RemoteException {
		super();
		this.randomNums = randomNums;
	}

    public static void main(String[] args) throws
            MalformedURLException, RemoteException, NotBoundException {

        look_up = (RmiInterface) Naming.lookup("//localhost/RandomNumbers");
        
        AccessRandomNumbers distObj = new AccessRandomNumbers(look_up.generateRandomNumbers());

        for(RandomNumCheck random:distObj.randomNums) {
        	Integer randomNum = random.getRandomNum();
        	for (int j=2;j<=randomNum-1;j++) {
				if (randomNum%j==0){
					random.setPrimeChk(Boolean.FALSE);
					break;
				}
			}
        	if (random.getPrimeChk()==null){
        		random.setPrimeChk(Boolean.TRUE);
        	}
        	System.out.println("Random Number Received from Randomizer Application is="+random.getRandomNum());
        }
        Naming.rebind("//localhost/PrimeNumbers", distObj);
    }

	@Override
	public List<RandomNumCheck> generateRandomNumbers() throws RemoteException {
		return randomNums;
	}
}