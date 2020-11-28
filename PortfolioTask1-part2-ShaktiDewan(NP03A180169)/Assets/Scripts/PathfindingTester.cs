using System.Collections;
using System.Collections.Generic;
using UnityEngine;
public class PathfindingTester : MonoBehaviour
{
    public float speed;
    // The A* manager.
    private AStarManager AStarManager = new AStarManager();
    // Array of possible waypoints.
    List<GameObject> Waypoints = new List<GameObject>();
    // Array of waypoint map connections. Represents a path.
    List<Connection> ConnectionArray = new List<Connection>();
    // The start and end target point.
    public GameObject start;
    public GameObject end;
    // Debug line offset.
    Vector3 OffSet = new Vector3(0, 0.3f, 0);
    int index = 0;
    bool goal = false;
    bool returned = false;
    public int parcels;
    public TextMesh agentStats;
    public TextMesh agentStats1;

    // Start is called before the first frame update
    void Start()
    {
        if (start == null || end == null)
        {
            Debug.Log("No start or end waypoints.");
            return;
        }
        // Find all the waypoints in the level.
        GameObject[] GameObjectsWithWaypointTag;
        GameObjectsWithWaypointTag = GameObject.FindGameObjectsWithTag("Waypoint");
        foreach (GameObject waypoint in GameObjectsWithWaypointTag)
        {
            WaypointCON tmpWaypointCon = waypoint.GetComponent<WaypointCON>();
            if (tmpWaypointCon)
            {
                Waypoints.Add(waypoint);
            }
        }
        // Go through the waypoints and create connections.
        foreach (GameObject waypoint in Waypoints)
        {
            WaypointCON tmpWaypointCon = waypoint.GetComponent<WaypointCON>();
            // Loop through a waypoints connections.
            foreach (GameObject WaypointConNode in tmpWaypointCon.Connections)
            {
                Connection aConnection = new Connection();
                aConnection.SetFromNode(waypoint);
                aConnection.SetToNode(WaypointConNode);
                AStarManager.AddConnection(aConnection);
            }
        }
        // Run A Star...
        ConnectionArray = AStarManager.PathfindAStar(start, end);
    }
    // Draws debug objects in the editor and during editor play (if option set).
    void OnDrawGizmos()
    {
        // Draw path.
        foreach (Connection aConnection in ConnectionArray)
        {
            Gizmos.color = Color.white;
            Gizmos.DrawLine((aConnection.GetFromNode().transform.position + OffSet),
            (aConnection.GetToNode().transform.position + OffSet));
        }
    }

    void GotoGoal()
    {
        if (shouldWait())
        {
            return;
        }
        GameObject currGoal = ConnectionArray[index].GetToNode();
        if (Vector3.Distance(currGoal.transform.position, this.transform.position) == 0)
        {
            index++;
            if (index >= ConnectionArray.Count)
            {
                Collection();
                index = ConnectionArray.Count - 1;
                return;
            }
            currGoal = ConnectionArray[index].GetToNode();
        }
        transform.position = Vector3.MoveTowards(transform.position, currGoal.transform.position, Time.deltaTime * speed);
        //for car turn
        var rot = ConnectionArray[index].GetToNode().transform.position - transform.position;
        rot.y = 0;
        transform.rotation = Quaternion.Slerp(transform.rotation, Quaternion.LookRotation(rot), 1);
    }

    bool shouldWait()
    {
        try
        {
            GameObject[] agents = GameObject.FindGameObjectsWithTag("Agent");
            foreach (GameObject agent in agents)
            {
                PathfindingTester script = agent.GetComponent<PathfindingTester>();
                if (agent.GetHashCode() == this.gameObject.GetHashCode())
                {
                    continue;
                }
                if (!script.goal && !script.returned)
                {
                    continue;
                }
                Connection currAgentPath = this.ConnectionArray[this.index];
                Connection agentPath = script.ConnectionArray[script.index];
                if (agentPath.GetToNode() == currAgentPath.GetFromNode())
                {
                    if (script.speed > this.speed)
                    {
                        return true;
                    }
                }
                if (agentPath.GetToNode() == currAgentPath.GetToNode())
                {
                    if (script.speed > this.speed)
                    {
                        return true;
                    }
                }
            }
            return false;
        }
        catch (System.ArgumentOutOfRangeException)
        {
            return false;
        }
    }
    void Collection()
    {
        goal = true;
        float minSpeed = (float)0.5 * speed;
        float newSpeed = (float)(parcels * 0.1) * speed;
        parcels = 10;//change made if deliver or collection is done
        if (newSpeed < minSpeed)
        {
            speed = minSpeed;
        }
        else
        {
            speed = newSpeed;
        }
    }
    void StopCollecting()
    {
        goal = true;
        returned = false;
    }

    void GoHome()
    {
        if (shouldWait())
        {
            return;
        }
        GameObject currGoal = ConnectionArray[index].GetFromNode();
        if (Vector3.Distance(currGoal.transform.position, this.transform.position) == 0)
        {
            index--;
            if (index < 0)
            {
                returned = true;
                return;
            }
            currGoal = ConnectionArray[index].GetFromNode();
        }
        transform.position = Vector3.MoveTowards(transform.position, currGoal.transform.position, Time.deltaTime * speed);
        //for car turn
        var rot = ConnectionArray[index].GetFromNode().transform.position - transform.position;
        rot.y = 0;
        transform.rotation = Quaternion.Slerp(transform.rotation, Quaternion.LookRotation(rot), 1);
    }
    // Update is called once per frame
    void Update()
    {
        if (!goal)
        {
            GotoGoal();
        }
        else
        {
            if (!returned)
            {
                GoHome();
            }
        }
        agentStats.text = "Packages: " + parcels;
        agentStats1.text = "Speed: " + speed +" m/s";

    }
}
