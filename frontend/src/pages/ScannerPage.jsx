import { Scanner, useDevices } from "@yudiel/react-qr-scanner";
import { DarkButton } from "../Components/DarkButton";
import { useEffect, useState, useRef } from "react";
import { useNavigate } from "react-router-dom";
import "../styles/ScannerPageStyle.css";
import { md5 } from "js-md5";

function ScannerPage() {
  const devices = useDevices();
  const [deviceIndex, setDeviceIndex] = useState(0);
  const [bar, setBar] = useState([]);
  const [barId, setBarId] = useState([]);
  const [loading, setLoading] = useState(false);
  const userId = localStorage.getItem("userId");

  const navigate = useNavigate();
  const scannerRef = useRef(null);

  const changeDevice = () => {
    if (devices.length <= 1) return;
    setDeviceIndex((prevIndex) => (prevIndex + 1) % devices.length);
  };

  const onScan = async (result) => {
    if (result[0].rawValue == md5(bar.currentBar.name)) {
      document.getElementById("scanner-output").innerText =
        "SUKCES! Powróć do gry";

      try {
        const response = await fetch("http://localhost:8080/bars/getRandomId");
        const data = await response.json();
        const barId = data;

        await fetch(`http://localhost:8080/game/${userId}?barId=${barId}`, {
          method: "PUT",
        });
      } catch (err) {
        console.log(err);
      }
    } else {
      document.getElementById("scanner-output").style.color = "red";
      document.getElementById("scanner-output").innerText =
        "NIE TEN BAR KOLEŻKO!";
    }
  };

  useEffect(() => {
    setLoading(true);
    //nizej id uzytkownika
    fetch("http://localhost:8080/game/" + userId)
      .then((response) => response.json())
      .then((data) => setBar(data))
      .catch((err) => console.log(err));
    setLoading(false);
  }, []);

  if (loading) {
    return <div>Loading...</div>;
  }
  return (
    <div className="navy-background">
      <div className="qr-scanner">
        <Scanner
          constraints={{
            aspectRatio: { ideal: 1 },
            deviceId: devices[deviceIndex]?.deviceId,
          }}
          onScan={onScan}
        />
      </div>
      <div id="scanner-utilities">
        <p id="scanner-output"></p>
        <div className="scanner-buttons">
          <div className="scan-button-left">
            <DarkButton onClick={() => navigate("/alkoholizacja")}>
              Powrót
            </DarkButton>
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
