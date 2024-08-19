import React, { useEffect, useState } from 'react';
import { useNavigate, useParams } from 'react-router-dom';
import '../styles/ViewEvidences.css';

function ViewEvidences() {
  const navigate = useNavigate();
  const { complaintId } = useParams(); // Get complaintId from URL params
  const [evidences, setEvidences] = useState([]); // State to hold evidence details

  // Function to handle logout
  const handleLogout = () => {
    localStorage.removeItem('user');
    navigate('/');
  };

  // Function to fetch evidences
  useEffect(() => {
    fetch(`http://localhost:8080/api/evidences/complaint/${complaintId}`)
      .then(response => response.json())
      .then(data => setEvidences(data))
      .catch(error => console.error('Error fetching evidences:', error));
  }, [complaintId]);

  return (
    <div className="dashboard">
      <div className="side-nav">
        <div className="logo">Dial100</div>
        <ul>
          <li><a href="/all-new-complaints">All New Complaints</a></li>
          <li><a href="/my-investigations">My Investigations</a></li>
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
          <h1>Complaint ID - {complaintId} - Evidences</h1>
          <div className="evidences-list">
            {evidences.length > 0 ? (
              evidences.map(evidence => (
                <div key={evidence.id} className="evidence-item">
                  <p><strong>Evidence ID:</strong> {evidence.evidenceId}</p>
                  <p><strong>Evidence Type:</strong> {evidence.evidenceType}</p>
                  <p><strong>File Path:</strong> {evidence.filePath}</p>
                  <p><strong>Upload Date:</strong> {new Date(evidence.uploadDate).toLocaleDateString()}</p>
                </div>
              ))
            ) : (
              <p>No evidences found for this complaint.</p>
            )}
          </div>
        </div>
      </div>
    </div>
  );
}

export default ViewEvidences;
