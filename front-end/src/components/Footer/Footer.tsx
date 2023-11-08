import React from "react";
import "./Footer.scss";
import Container from "../ui/Container/Container";
import { Link } from "react-router-dom";

const Footer = () => {
    return (
        <footer className="footer">
            <Container>
                <div className="footer-wrapper">
                    <div className="footer-section">
                        <h3 className="footer-heading">Follow Us</h3>
                        <ul className="footer-list">
                            <li>
                                <a target="_blank" href="https://www.facebook.com/" className="footer-link" rel="noreferrer">
                                    Facebook
                                </a>
                            </li>
                            <li>
                                <a target="_blank" href="https://twitter.com/home" className="footer-link" rel="noreferrer">
                                    Twitter
                                </a>
                            </li>
                            <li>
                                <a target="_blank" href="https://www.instagram.com/" className="footer-link" rel="noreferrer">
                                    Instagram
                                </a>
                            </li>
                        </ul>
                    </div>
                    <div className="footer-section">
                        <h3 className="footer-heading">Quick Links</h3>
                        <ul className="footer-list">
                            <li>
                                <Link to="/" className="footer-link">
                                    Home
                                </Link>
                            </li>
                            <li>
                                <a target="_blank" href="https://www.google.com/" className="footer-link" rel="noreferrer">
                                    About Us
                                </a>
                            </li>
                        </ul>
                    </div>
                    <div className="footer-section">
                        <h3 className="footer-heading">Contact Us</h3>
                        <ul className="footer-list">
                            <li className="footer-text">
                                Address:{" "}
                                <a
                                    target="_blank"
                                    className="footer-link"
                                    href="https://maps.example.com"
                                    rel="noreferrer"
                                >
                                    1234 Street, City, Country
                                </a>
                            </li>
                            <li className="footer-text">
                                Email:{" "}
                                <a
                                    target="_blank"
                                    className="footer-link"
                                    href="mailto:example@example.com"
                                    rel="noreferrer"
                                >
                                    example@example.com
                                </a>
                            </li>
                            <li className="footer-text">
                                Phone:{" "}
                                <a
                                    target="_blank"
                                    className="footer-link"
                                    href="tel:1234567890"
                                    rel="noreferrer"
                                >
                                    123-456-7890
                                </a>
                            </li>
                        </ul>
                    </div>
                </div>
            </Container>
        </footer>
    );
};

export default Footer;
