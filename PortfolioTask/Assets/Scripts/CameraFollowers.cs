
using UnityEngine;

public class CameraFollowers : MonoBehaviour
{
    public Transform target;

    public float smoothspeed = 0.125f;
    public Vector3 offset;
    // Update is called once per frame
    void LateUpdate()
    {
        transform.position = target.position + offset;
    }
}
