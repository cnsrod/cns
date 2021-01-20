import java.util.Scanner;
public class BellmanFord
{
private int dist[];
private int noofvert;
public static final int MAXVAL = 999;


//
public BellmanFord(int noofvert)
{
	this.noofvert = noofvert;
	dist = new int[noofvert + 1];
}



//
public void BellmanFordEval(int source, int adjmtx[][])
{
	for (int node = 1; node <= noofvert; node++)
	{
		dist[node] = MAXVAL;
	}
	dist[source] = 0;
	for (int node = 1; node <= noofvert - 1; node++)
	{
		for (int sn = 1; sn<= noofvert; sn++)
		{
			for (int dn = 1; dn<= noofvert; dn++)
			{
				if (adjmtx[sn][dn] != MAXVAL)
				{
					if (dist[dn] > dist[sn] + adjmtx[sn][dn])
						dist[dn] = dist[sn] + adjmtx[sn][dn];
				}
			}
		}
	}
	System.out.println("After Nth Iteration");
	for (int v = 1; v <= noofvert; v++)
	{
		System.out.println("distance of source " + source + " to " + v + " is " + dist[v]);
	}
}




//main
public static void main(String[] args)
{
	int noofvert = 0;
	int source;
	Scanner scanner = new Scanner(System.in);
	System.out.println("Enter the number of vertices");
	noofvert = scanner.nextInt();
	int adjmtx[][] = new int[noofvert + 1][noofvert + 1];
	System.out.println("Enter the adjacency matrix");
	for (int sn = 1; sn<= noofvert; sn++)
	{
		for (int dn = 1; dn<= noofvert; dn++)
		{
			adjmtx[sn][dn] = scanner.nextInt();
			if (sn == dn)
			{
				adjmtx[sn][dn] = 0;
				continue;
			}
			if (adjmtx[sn][dn] == 0)
			{
				adjmtx[sn][dn] = MAXVAL;
			}
		}
	}
System.out.println("Enter the source vertex");
source = scanner.nextInt();
BellmanFord bellmanford = new BellmanFord(noofvert);
bellmanford.BellmanFordEval(source, adjmtx);
scanner.close();
}
}