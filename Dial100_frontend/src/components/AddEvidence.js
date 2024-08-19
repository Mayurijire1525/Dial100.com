import React, { useState } from 'react';
import { useNavigate, useParams } from 'react-router-dom';
import '../styles/AddEvidence.css';

function AddEvidence() {
  const { complaintId } = useParams(); // Get the complaintId from the URL
  const [evidenceType, setEvidenceType] = useState('');
  const [filePath, setFilePath] = useState('');
  const [uploadDate, setUploadDate] = useState('');
  const [successMessage, setSuccessMessage] = useState('');
  const navigate = useNavigate();

  // Retrieve and parse user data from local storage
  const userData = JSON.parse(localStorage.getItem('user'));
    
  if (!userData || !userData.userId) {
    console.error('User ID not found in local storage.');
    return;
  }

  // Function to handle form submission
  const handleSubmit = (e) => {
    e.preventDefault();

    // Prepare the data to be sent
    const formData = {
      evidenceType,
      filePath,
      uploadDate,
      complaintId: parseInt(complaintId), // Ensure complaintId is an integer
    };

    // Submit the form data
    fetch('http://localhost:8080/api/evidences', {  // Endpoint for creating evidence
      method: 'POST',
      headers: { 'Content-Type': 'application/json' },
      body: JSON.stringify(formData),
    })
      .then((response) => response.json())
      .then(() => {
        // Clear the form fields
        setEvidenceType('');
        setFilePath('');
        setUploadDate('');
        
        // Set the success message
        setSuccessMessage('Evidence submitted successfully!');
        
        // Optionally, hide the message after a few seconds
        setTimeout(() => {
          setSuccessMessage('');
        }, 5000);
      })
      .catch((error) => console.error('Error adding evidence:', error));
  };

  // Function to handle logout
  const handleLogout = () => {
    localStorage.removeItem('user');
    navigate('/');
  };

  return (
    <div className="dashboard">
      <div className="side-nav">
        <div className="logo">Dial100</div>
        <ul>
          <li><a href="/register-complaint">Register Complaint</a></li>
          <li><a href="/view-complaints">View Complaints</a></li>
        </ul>
      </div>
      
      <div className="content-area">
        <div className="top-nav">
          <input type="text" className="search-bar" placeholder="Search here..." />
          <div className="profile-options">
            <a href="/profile">Your Profile</a>
            <a href="/" onClick={handleLogout}>Logout</a>
          </div>
        </div>

        <div className="main-content">
          <h1>Add Evidence for Complaint ID {complaintId}</h1>
          <form onSubmit={handleSubmit} className="add-evidence-form">
            <label htmlFor="evidenceType">Evidence Type:</label>
            <select
              id="evidenceType"
              value={evidenceType}
              onChange={(e) => setEvidenceType(e.target.value)}
              required
            >
              <option value="" disabled>Select Evidence Type</option>
              <option value="PHYSICAL">Physical</option>
              <option value="DOCUMENTARY">Documentary</option>
              <option value="WITNESS">Witness</option>
              <option value="DIGITAL">Digital</option>
              <option value="FORENSIC">Forensic</option>
            </select>

            <label htmlFor="filePath">File Path:</label>
            <input
              type="text"
              id="filePath"
              value={filePath}
              onChange={(e) => setFilePath(e.target.value)}
              required
            />

            <label htmlFor="uploadDate">Upload Date:</label>
            <input
              type="date"
              id="uploadDate"
              value={uploadDate}
              onChange={(e) => setUploadDate(e.target.value)}
              required
            />

            <button type="submit">Submit Evidence</button>
          </form>

          {/* Display success message */}
          {successMessage && <p className="success-message">{successMessage}</p>}
        </div>
      </div>
    </div>
  );
}

export default AddEvidence;
