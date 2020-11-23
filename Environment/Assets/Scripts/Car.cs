using System.Collections;
using System.Collections.Generic;
using UnityEngine;

public class Car : MonoBehaviour
{
    public WheelCollider frontDriveCol, frontPassCol;
    public WheelCollider backDriveCol, backPassCol;

    public Transform frontDriver, FrontPass;
    public Transform backDriver, backPass;

    public float steerAngle = 25.0f;
    public float motorForce = 1500f;

    public float steerAngl;

    float h, v;
    //public float speed;
    // Start is called before the first frame update
    void Start()
    {

    }

    // Update is called once per frame
    void FixedUpdate()
    {
        Inputs();
        Drive();
        SteerCar();

        UpdateWheelPos(frontDriveCol, frontDriver);
        UpdateWheelPos(frontPassCol, FrontPass);
        UpdateWheelPos(backDriveCol, backDriver);
        UpdateWheelPos(backPassCol, backPass);
       // transform.Translate(speed * Input.GetAxis("Vertical") * Time.deltaTime, 0f, speed * Input.GetAxis("Horizontal") * Time.deltaTime);
    }

    void Inputs() {
        h = Input.GetAxis("Horizontal");
        v = Input.GetAxis("Vertical");
    }

    void Drive()
    {
        backDriveCol.motorTorque = v * motorForce;
        backPassCol.motorTorque = v * motorForce;
    }

    void SteerCar() {
        steerAngl = steerAngle * h;
        frontDriveCol.steerAngle = steerAngl;
        frontPassCol.steerAngle = steerAngl;
    }

    void UpdateWheelPos(WheelCollider col, Transform t) {
        Vector3 pos = t.position;
        Quaternion rot = t.rotation;

        col.GetWorldPose(out pos, out rot);
        t.position = pos;
        t.rotation = rot;
    }

}
