import { Scanner, useDevices } from "@yudiel/react-qr-scanner";
import { DarkButton } from "../Components/DarkButton";
import { useEffect, useState, useRef } from "react";
import { useNavigate } from "react-router-dom";
import "../styles/ScannerPageStyle.css";

function ScannerPage() {
  const devices = useDevices();
  const [deviceIndex, setDeviceIndex] = useState(0); // Initialize deviceIndex to 0
  const [qrData, setQrData] = useState(null);
  const navigate = useNavigate();
  const scannerRef = useRef(null); // Ref to Scanner component

  useEffect(() => {
    console.log("Devices:", devices);
    console.log("Current Device Index:", deviceIndex);
    console.log("Current Device ID:", devices[deviceIndex]?.deviceId);
  }, [devices, deviceIndex]);

  useEffect(() => {
    // Reset scanner when deviceIndex changes
    if (scannerRef.current) {
      scannerRef.current.stop(); // Stop current scanner
      scannerRef.current.start(); // Start with new deviceId
    }
  }, [deviceIndex]);

  const changeDevice = () => {
    if (devices.length <= 1) return;
    setDeviceIndex((prevIndex) => (prevIndex + 1) % devices.length);
  };

  const onScan = (result) => {
    if (result) {
      setQrData(result);
      console.log("Scanned Result:", result);
    }
  };

  return (
    <div className="navy-background">
      <div className="qr-scanner">
        <Scanner
          constraints={{
            aspectRatio: { ideal: 1 },
            facingMode: { ideal: "environment" },
            focusMode: { ideal: "continuous" },
          }}
          onScan={onScan}
          deviceId={devices[deviceIndex]?.deviceId || null}
        />
      </div>
      <div id="scanner-utilities">
        <div className="scanner-output">
          {qrData && <p className="scanning-status">Scanned: {qrData}</p>}
        </div>
        <div className="scanner-buttons">
          <div className="scan-button-left">
            <DarkButton onClick={() => navigate(-1)}>Powrót</DarkButton>
          </div>
          <div className="scan-button-right">
            {devices.length > 1 && (
              <DarkButton onClick={changeDevice}>
                <i className="fa-solid fa-camera-rotate"></i>
              </DarkButton>
            )}
          </div>
        </div>
      </div>
    </div>
  );
}

export default ScannerPage;
