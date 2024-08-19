import React from 'react';
import '../styles/ProfilePage.css'; // Assuming CSS file is in the styles folder

function ProfileComponent() {
  // Get user data from localStorage and parse it
  const user = JSON.parse(localStorage.getItem('user'));

  // Destructure the user data
  const { name, email, phone, address, role } = user;

  return (
    <div className="profile-container">
      <h1>Your Profile</h1>
      <div className="profile-details">
        <div className="profile-item">
          <span className="label">Name:</span>
          <span className="value">{name}</span>
        </div>
        <div className="profile-item">
          <span className="label">Email:</span>
          <span className="value">{email}</span>
        </div>
        <div className="profile-item">
          <span className="label">Phone:</span>
          <span className="value">{phone}</span>
        </div>
        <div className="profile-item">
          <span className="label">Address:</span>
          <span className="value">{address}</span>
        </div>
        <div className="profile-item">
          <span className="label">Role:</span>
          <span className="value">{role}</span>
        </div>
      </div>
    </div>
  );
}

export default ProfileComponent;
