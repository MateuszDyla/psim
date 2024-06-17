import { Scanner, useDevices } from "@yudiel/react-qr-scanner";
import { DarkButton } from "../Components/DarkButton";
import { useEffect, useState, useRef } from "react";
import { useNavigate } from "react-router-dom";
import "../styles/ScannerPageStyle.css";

function ScannerPage() {
    const devices = useDevices();
    const [deviceIndex, setDeviceIndex] = useState(0); // Initialize deviceIndex to 0
    const navigate = useNavigate();
    const scannerRef = useRef(null); // Ref to Scanner component

    const changeDevice = () => {
        if (devices.length <= 1) return;
        setDeviceIndex((prevIndex) => (prevIndex + 1) % devices.length);
    };

    const onScan = (result) => {
        if (result) {
            console.log("Scanned Result:", result[0].rawValue);
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
                        deviceId: devices[deviceIndex]?.deviceId,
                    }}
                    onScan={onScan}
                />
            </div>
            <div id="scanner-utilities">
                <div className="scanner-output">sadasdasd</div>
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