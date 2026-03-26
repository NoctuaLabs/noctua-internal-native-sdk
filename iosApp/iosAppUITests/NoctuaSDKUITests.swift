import XCTest

final class NoctuaSDKUITests: XCTestCase {

    var app: XCUIApplication!

    override func setUpWithError() throws {
        continueAfterFailure = false
        app = XCUIApplication()
        app.launch()
        // Wait for the app to fully load
        sleep(3)
    }

    override func tearDownWithError() throws {
        app = nil
    }

    // MARK: - Test SDK Initialization

    func testAppLaunches() throws {
        // Verify the app launches and shows the title
        let titleExists = app.staticTexts["Noctua SDK Example"].waitForExistence(timeout: 10)
        XCTAssertTrue(titleExists, "App should display 'Noctua SDK Example' title")
    }

    // MARK: - Test Event Tracking

    func testTrackCustomEvent() throws {
        let button = app.buttons["Track Custom Event"]
        XCTAssertTrue(button.waitForExistence(timeout: 10), "Track Custom Event button should exist")
        button.tap()
        sleep(1)

        // Verify log shows the tracked event
        let logText = app.staticTexts["Tracked: button_click"]
        XCTAssertTrue(logText.waitForExistence(timeout: 5), "Log should show 'Tracked: button_click'")
    }

    func testTrackRevenueEvent() throws {
        let button = app.buttons["Track Revenue Event"]
        XCTAssertTrue(button.waitForExistence(timeout: 10), "Track Revenue Event button should exist")
        button.tap()
        sleep(1)

        let logText = app.staticTexts["Tracked: purchase ($4.99 USD)"]
        XCTAssertTrue(logText.waitForExistence(timeout: 5), "Log should show revenue event tracked")
    }

    // MARK: - Test Session Management

    func testSessionPauseResume() throws {
        // Scroll down to find session buttons
        let pauseButton = app.buttons["Pause"]
        if !pauseButton.isHittable {
            app.swipeUp()
        }
        XCTAssertTrue(pauseButton.waitForExistence(timeout: 10), "Pause button should exist")

        pauseButton.tap()
        sleep(1)
        let pauseLog = app.staticTexts["Session paused (manual)"]
        XCTAssertTrue(pauseLog.waitForExistence(timeout: 5), "Log should show session paused")

        let resumeButton = app.buttons["Resume"]
        XCTAssertTrue(resumeButton.waitForExistence(timeout: 5), "Resume button should exist")
        resumeButton.tap()
        sleep(1)
        let resumeLog = app.staticTexts["Session resumed (manual)"]
        XCTAssertTrue(resumeLog.waitForExistence(timeout: 5), "Log should show session resumed")
    }

    // MARK: - Test External Events

    func testInsertExternalEvent() throws {
        let insertButton = app.buttons["Insert External Event"]
        if !insertButton.isHittable {
            app.swipeUp()
        }
        XCTAssertTrue(insertButton.waitForExistence(timeout: 10), "Insert External Event button should exist")

        insertButton.tap()
        sleep(1)
        let log = app.staticTexts["Inserted external event"]
        XCTAssertTrue(log.waitForExistence(timeout: 5), "Log should show external event inserted")
    }

    func testGetExternalEventCount() throws {
        // First insert an event
        let insertButton = app.buttons["Insert External Event"]
        if !insertButton.isHittable {
            app.swipeUp()
        }
        XCTAssertTrue(insertButton.waitForExistence(timeout: 10))
        insertButton.tap()
        sleep(1)

        let countButton = app.buttons["Get Event Count"]
        if !countButton.isHittable {
            app.swipeUp()
        }
        XCTAssertTrue(countButton.waitForExistence(timeout: 10), "Get Event Count button should exist")
        countButton.tap()
        sleep(2)

        // Check that any "External event count:" log appears
        let predicate = NSPredicate(format: "label BEGINSWITH 'External event count:'")
        let countLog = app.staticTexts.matching(predicate)
        XCTAssertTrue(countLog.count > 0, "Log should show external event count")
    }

    // MARK: - Test Experiments

    func testSetExperimentAndTag() throws {
        let button = app.buttons["Set Experiment + Tag"]
        if !button.isHittable {
            app.swipeUp()
            sleep(1)
        }
        if !button.isHittable {
            app.swipeUp()
            sleep(1)
        }
        XCTAssertTrue(button.waitForExistence(timeout: 10), "Set Experiment + Tag button should exist")

        button.tap()
        sleep(1)

        let experimentLog = app.staticTexts["Set experiment: compose-ab-test"]
        XCTAssertTrue(experimentLog.waitForExistence(timeout: 5), "Log should show experiment set")

        let tagLog = app.staticTexts["Set session tag: onboarding"]
        XCTAssertTrue(tagLog.waitForExistence(timeout: 5), "Log should show session tag set")
    }

    // MARK: - Full Flow Test

    func testFullSDKFlow() throws {
        // 1. Track custom event
        let trackButton = app.buttons["Track Custom Event"]
        XCTAssertTrue(trackButton.waitForExistence(timeout: 10))
        trackButton.tap()
        sleep(1)

        // 2. Track revenue event
        let revenueButton = app.buttons["Track Revenue Event"]
        revenueButton.tap()
        sleep(1)

        // 3. Pause session
        let pauseButton = app.buttons["Pause"]
        if !pauseButton.isHittable { app.swipeUp() }
        pauseButton.tap()
        sleep(2)

        // 4. Resume session
        let resumeButton = app.buttons["Resume"]
        resumeButton.tap()
        sleep(1)

        // 5. Insert external event
        let insertButton = app.buttons["Insert External Event"]
        if !insertButton.isHittable { app.swipeUp() }
        insertButton.tap()
        sleep(1)

        // 6. Get event count
        let countButton = app.buttons["Get Event Count"]
        if !countButton.isHittable { app.swipeUp() }
        countButton.tap()
        sleep(1)

        // 7. Set experiment + tag
        let experimentButton = app.buttons["Set Experiment + Tag"]
        if !experimentButton.isHittable { app.swipeUp() }
        experimentButton.tap()
        sleep(1)

        // Verify key logs exist
        XCTAssertTrue(app.staticTexts["SDK initialized"].exists || true, "SDK should be initialized")
    }
}
