package com.program.test.remote;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.List;

import com.program.test.local.RandomNumCheck;

public interface RmiInterface extends Remote{
	    List<RandomNumCheck> generateRandomNumbers() throws RemoteException;
}
