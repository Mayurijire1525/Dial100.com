import React from 'react';
import '../styles/HeroSection.css';

function HeroSection() {
  return (
    <div className="hero-section">
      <h1>Report Crime, Stay Safe</h1>
      <p>Welcome to Dial100 Portal. Your safety, our priority.</p>
      <a href="/register" className="cta-button">Register Complaint</a>
    </div>
  );
}

export default HeroSection;
