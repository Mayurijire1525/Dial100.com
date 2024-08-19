import React, { useState, useEffect } from 'react';
import { useNavigate } from 'react-router-dom'; // Import useNavigate for redirection
import '../styles/ComplaintForm.css';

function ComplaintForm() {
  const [complaint, setComplaint] = useState({
    dateFiled: '',
    description: '',
    status: 'new',
    crimeId: '',
    crimeType: '',
    crimeDescription: '',
    penalty: ''
  });

  const [crimeOptions, setCrimeOptions] = useState([]);
  const [successMessage, setSuccessMessage] = useState(''); // State for success message
  const navigate = useNavigate(); // Hook for redirection

  useEffect(() => {
    // Fetch all crime options for the Crime ID field
    fetch('http://localhost:8080/api/crimes')
      .then(response => response.json())
      .then(data => setCrimeOptions(data))
      .catch(error => console.error('Error fetching crime options:', error));
  }, []);

  useEffect(() => {
    if (complaint.crimeId) {
      // Fetch crime details when Crime ID changes
      fetch(`http://localhost:8080/api/crimes/${complaint.crimeId}`)
        .then(response => response.json())
        .then(data => {
          setComplaint(prevComplaint => ({
            ...prevComplaint,
            crimeType: data.crimeType,
            crimeDescription: data.description,
            penalty: data.penalty
          }));
        })
        .catch(error => console.error('Error fetching crime details:', error));
    }
  }, [complaint.crimeId]);

  const handleChange = (e) => {
    setComplaint({ ...complaint, [e.target.name]: e.target.value });
  };

  const handleSubmit = (e) => {
    e.preventDefault();

    // Retrieve and parse user data from local storage
    const userData = JSON.parse(localStorage.getItem('user'));
    
    if (!userData || !userData.userId) {
      console.error('User ID not found in local storage.');
      return;
    }
    
    // Create payload with user_id
    const payload = {
      ...complaint,
      userId: userData.userId // Add userId to the payload
    };
    
    // Submit complaint
    fetch('http://localhost:8080/api/complaints', {
      method: 'POST',
      headers: {
        'Content-Type': 'application/json'
      },
      body: JSON.stringify(payload)
    })
    .then(response => response.json())
    .then(data => {
      setSuccessMessage('Complaint successfully submitted!'); // Set success message
      setTimeout(() => {
        navigate('/plaintiff-dashboard'); // Redirect after 2 seconds
      }, 2000);
    })
    .catch(error => console.error('Error submitting complaint:', error));
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
            <a href="/">Logout</a>
          </div>
        </div>

        <div className="main-content">
          <h1>Register Complaint</h1>
          <form className="complaint-form" onSubmit={handleSubmit}>
            <div className="form-group">
              <label>Date Filed:</label>
              <input
                type="date"
                name="dateFiled"
                value={complaint.dateFiled}
                onChange={handleChange}
                required
              />
            </div>
            <div className="form-group">
              <label>Description:</label>
              <textarea
                name="description"
                placeholder="Enter your description"
                value={complaint.description}
                onChange={handleChange}
                required
              />
            </div>
            <div className="form-group">
              <label>Status:</label>
              <input
                type="text"
                name="status"
                placeholder="Enter status"
                value={complaint.status}
                onChange={handleChange}
                disabled
              />
            </div>
            <div className="form-group">
              <label>Crime ID:</label>
              <select
                name="crimeId"
                value={complaint.crimeId}
                onChange={handleChange}
                required
              >
                <option value="">Select Crime ID</option>
                {crimeOptions.map(crime => (
                  <option key={crime.crimeId} value={crime.crimeId}>
                    {crime.crimeId}
                  </option>
                ))}
              </select>
            </div>
            <div className="form-group">
              <label>Crime Type:</label>
              <input
                type="text"
                name="crimeType"
                value={complaint.crimeType}
                onChange={handleChange}
                disabled
              />
            </div>
            <div className="form-group">
              <label>Crime Description:</label>
              <textarea
                name="crimeDescription"
                placeholder="Fetched Description"
                value={complaint.crimeDescription}
                onChange={handleChange}
                disabled
              />
            </div>
            <div className="form-group">
              <label>Penalty:</label>
              <textarea
                name="penalty"
                placeholder="Fetched Penalty"
                value={complaint.penalty}
                onChange={handleChange}
                disabled
              />
            </div>
            <button type="submit">Submit Complaint</button>
          </form>
          {successMessage && <p className="success-message">{successMessage}</p>} {/* Display success message */}
        </div>
      </div>
    </div>
  );
}

export default ComplaintForm;
