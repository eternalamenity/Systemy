package sososososopy;

import java.io.IOException;
import java.nio.file.FileAlreadyExistsException;
import java.nio.file.NoSuchFileException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import javax.management.openmbean.OpenDataException;


public class FileSystem
{
    //public static final int FILE_OPEN = 1;
    public static final int FILE_EDIT = 2;
    
    private int discCapacity;
    private int clusterCapacity;
    private Map<String, Integer> fileList;
    private Map<Integer, Cluster> clusterList;
    private Map<Integer, Integer> lockedClusters;
    private ArrayList<String> editFiles;
    
    public FileSystem(int discCapacity, int clusterCapacity)
    {
        this.discCapacity=discCapacity;
        this.clusterCapacity=clusterCapacity;
    }
    
    public FileSystem create()
    {
        this.fileList = new HashMap<>();
        this.clusterList = new HashMap<>();
        this.lockedClusters = new HashMap<>();
        
        for(int i=0;i<this.discCapacity;i++)
        {
            this.clusterList.put(i, new Cluster(i, -1,null));
            this.lockedClusters.put(i, 0);
        }
        return this;
    }
    
    private ArrayList<String> chunkData(String data, int bits)
    {
        ArrayList<String> outputArr = new ArrayList<>();
        if(data.length()<bits)
        {
            outputArr.add(data);
        }
        else
        {
            int chunksCount = (int) Math.ceil(data.length()/bits); //.ceil?
            int lastIndex = 0;
            for(int i=0; i<chunksCount;i++)
            {
                StringBuilder s = new StringBuilder();
                for(int x = 0;x<bits;x++)
                {
                    s.append(data.charAt(lastIndex));
                    lastIndex = lastIndex + 1;
                }
                outputArr.add(s.toString());
            }
            String rest = data.substring(chunksCount * bits);
            if(rest.length()>0)
                outputArr.add(rest);
        }
        return outputArr;
    }
    
    private void directoryExists(String directory) throws FileAlreadyExistsException
    {
        if(this.fileList.containsKey(directory))
        {
            throw new FileAlreadyExistsException("This file already exists");
        }
    }
    
    private ArrayList<Integer> alocateFreeClusters(int clusters) throws IOException
    {
        ArrayList<Integer> freeSpace = new ArrayList<>();
        for(Map.Entry<Integer,Integer> pair : this.lockedClusters.entrySet()) //?
        {
            if(freeSpace.size()==clusters)
            {
                break;
            }
            if(pair.getValue()==0)
            {
                freeSpace.add(pair.getKey()); //?
            }
        }
        if(freeSpace.size() < clusters)
        {
            throw new IOException("There is not enough space on the disc");
        }
        return freeSpace;
    }
     
    public void saveOnDisc(String fileName, String data) throws IOException 
    {
        // First check is file exists
        this.directoryExists(fileName);
        // Chunk data to cluster capacity arrays
        ArrayList<String> str = chunkData(data, this.clusterCapacity);
        // Check is free space on disc
        ArrayList<Integer> freeClusters = this.alocateFreeClusters(str.size());

        int actualClusterIndex = 0;
        for(String s: str)
        {
            int clusterId = freeClusters.get(actualClusterIndex);
            int nextClusterId = -1;
            if(actualClusterIndex + 1 <  freeClusters.size())
            {
                nextClusterId = freeClusters.get(actualClusterIndex + 1);
            }
            this.clusterList.put(clusterId, new Cluster(clusterId, nextClusterId, s));
            this.lockedClusters.put(clusterId, 1);
            actualClusterIndex++;
        }
        this.fileList.put(fileName, freeClusters.get(0));
    }   
    
    private boolean isFileExists(String fileName) 
    {
        return this.fileList.containsKey(fileName);
    }
    
    
    private ArrayList<Cluster> buildClusters(ArrayList<Cluster> clusters, int startClusterId)
    {
        int nextClusterId = this.clusterList.get(startClusterId).getNextClusterID();
        if(nextClusterId != -1)
        {
            clusters.add(this.clusterList.get(startClusterId));
            return this.buildClusters(clusters, this.clusterList.get(startClusterId).getNextClusterID());
        }
        else if(nextClusterId == -1)
        {
            clusters.add(this.clusterList.get(startClusterId));
        }
        return clusters;
    }
 
    public boolean removeFile(String file)
    {
        if(this.isFileExists(file))
        {
            int dir = this.fileList.get(file);
            ArrayList<Cluster> clusters = this.buildClusters(new ArrayList<>(),dir);
            for(Cluster c: clusters)
            {
                this.clusterList.put(c.getClusterID(), new Cluster(c.getClusterID(),-1,null));
                this.lockedClusters.put(c.getClusterID(), 0);
            }
            this.fileList.remove(file);
            return true;
        }
        return false;
    }
    
    public String readFile(String fileName) throws NoSuchFileException 
    {
        if (this.isFileExists(fileName)) {
            ArrayList<Cluster> clusters = this.buildClusters(new ArrayList<>(), this.fileList.get(fileName));
            StringBuilder output = new StringBuilder();
            for (Cluster c : clusters) {
                output.append(c.getData());
            }
            System.out.print(fileName + ": ");
            return output.toString();
        } else {
            throw new NoSuchFileException("There is no file with name: " + fileName);
        }
    }
    
     private boolean isFileOpenToEdit(String fileName) throws OpenDataException 
    {
        if (this.editFiles.contains(fileName))
        {
            throw new OpenDataException("This file " + fileName + " is already open!");
        }
        return false;
    }


    /*public void editFile(String fileName, String data, String newFileName, String newData) throws OpenDataException, NoSuchFileException, IOException
    {
        // First check is file exists
        this.directoryExists(fileName);
        // Check is free space on disc
        ArrayList<Integer> freeClusters = this.alocateFreeClusters(str.size());
        
        int length = data.length();
        int empty=0;
        if(length<32)
        {
            empty = 32 - length;
        }
        else if(length>32)
        {
         while(length>32)
         {
             length = length - 32;
         }
         empty = 32 - length;
        }
        // Chunk data to cluster capacity arrays
        ArrayList<String> str = chunkData(newData, empty);
        if(this.isFileExists(fileName))
        {
            int dir = this.fileList.get(fileName);
            ArrayList<Cluster> clusters = this.buildClusters(new ArrayList<>(),dir);
            for(Cluster c: clusters)
            {
                this.clusterList.put(c.getClusterID(), new Cluster(c.getClusterID(),c.getNextClusterID(),newData));
                this.lockedClusters.put(c.getClusterID(), 1);
            }
            this.fileList.put(fileName, freeClusters.get(0)); // czym jest tutaj to freeClusters.get(0)
        }
    }*/
       
    
    public int getDiscCapacity() 
    {
        return discCapacity;
    }

    public int getClusterCapacity() 
    {
        return clusterCapacity;
    }
    
    public void showClusters() 
    {
        for (Map.Entry<Integer, Cluster> c : this.clusterList.entrySet()) 
        {
            System.out.println("Cluster number: " + c.getValue().getClusterID() + " next cluster: " + c.getValue().getNextClusterID() + " with data: " + c.getValue().getData());
        }
    }

}
