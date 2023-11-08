import React from "react";
import Container from "../../ui/Container/Container";
import "./DoctorBanner.scss";

const DoctorBanner = () => {
    return (
        <aside>
            <Container>
                <div className="doctor">
                    <div className="doctor-section">
                        <h3 className="doctor-title">
                            Запишіться на прийом до висококваліфікованого лікаря
                        </h3>
                        <button className="doctor-button">Записатись</button>
                    </div>

                    <img
                        className="doctor-image"
                        src="https://img.freepik.com/free-photo/doctor-with-his-arms-crossed-over-white-background_1368-5789.jpg?w=740&t=st=1699352213~exp=1699352813~hmac=275d2e925092d12eec2d956f0728b5ada7b035cbb30745e2b5d7c6b2c4b09eb3"
                        alt="doctor"
                    />
                </div>

            </Container>
        </aside>
    );
};

export default DoctorBanner;
