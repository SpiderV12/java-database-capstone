# Smart Clinic – Schema & Architecture

## 1) Architecture Summary

يعتمد النظام على معمارية ثلاثية الطبقات (Presentation / Application / Data) باستخدام Spring Boot. واجهة العرض تقدّم لوحتَي تحكم HTML عبر **Thymeleaf MVC** (Controllers من نوع Web/MVC) وكذلك **REST APIs** (Controllers من نوع REST) لاستهلاكها من SPA/موبايل. طبقة التطبيق تحتوي **Services** مسؤولة عن منطق الأعمال والتحقق والتعاملات (transactions). طبقة البيانات تستخدم **MySQL** للبيانات العلائقية عبر **Spring Data JPA** (كيانات Entities ومستودعات Repositories) و**MongoDB** للبيانات المرنة/غير المهيكلة عبر **Spring Data MongoDB** (وثائق Documents ومستودعات Mongo). تنظيم الحزم المقترح:  
`controller.web` لصفحات Thymeleaf، `controller.api` لنقاط REST، `service` للمنطق، `repository.jpa` و`repository.mongo` للوصول للبيانات، و`model.entity` (JPA) و`model.document` (Mongo).

## 2) Numbered Flow – Request/Response

### A) تدفّق لوحات HTML (Thymeleaf MVC)

1. **المستخدم/المتصفح** يطلب صفحة (GET) مثل `/dashboard/appointments`.
2. يصل الطلب إلى **MVC Controller** داخل `controller.web.*` (مثل `DashboardController`).
3. يستدعي الـController طبقة **Service** المناسبة (مثل `AppointmentService`) مع المعايير.
4. تقوم الـService بالتحقق من المدخلات، إدارة **الترانزاكشن** عند الحاجة، واستدعاء المستودعات:
   - إن كان الطلب بيانات علائقية (مواعيد/أطباء/مرضى) → تستدعي **JPA Repository** داخل `repository.jpa.*` (MySQL).
   - إن كان الطلب بيانات مرنة (وصفات/ملاحظات/سجلات) → تستدعي **Mongo Repository** داخل `repository.mongo.*` (MongoDB).
5. **JPA** يولّد استعلامات SQL إلى **MySQL** (علاقات، قيود، join…)، و**Spring Data MongoDB** يرسل عمليات CRUD إلى **MongoDB** (وثائق/مصفوفات).
6. تعيد المستودعات النتائج إلى الـService، التي تجمع/تحوّل البيانات إلى **View Model**.
7. يعيد الـController **ModelAndView** إلى قالب **Thymeleaf** (مثل `appointments.html`).
8. يتم **Render** الصفحة وإرجاع **HTTP 200 + HTML** للمتصفح.

### B) تدفّق REST API (لـ SPA/موبايل/تكامل خارجي)

1. **عميل REST** يرسل طلبًا (مثلاً: `POST /api/appointments`) مع JSON.
2. يصل الطلب إلى **REST Controller** داخل `controller.api.*` (مثل `AppointmentApiController`)، تُطبّق **Validation** (@Valid).
3. يستدعي الـController طبقة **Service** لإجراء منطق الأعمال (التحقق من التعارض، صلاحيات، قواعد المجال).
4. تقوم الـService بالتعامل مع طبقة البيانات:
   - إنشاء/تحديث كيانات عبر **JPA Repositories** → تخزين في **MySQL**.
   - عند الحاجة، إنشاء/قراءة وثائق مرتبطة (مثل وصفة مرتبطة بموعد) عبر **Mongo Repositories** → **MongoDB**.
5. تُدار **المعاملة (Transaction)** حول عمليات MySQL (ويمكن استخدام outbox/أحداث للتنسيق مع Mongo عند السيناريوهات المعقدة).
6. تعود النتائج إلى الـController على شكل DTOs.
7. يعيد الـController **HTTP Response** (مثلاً 201 Created أو 200 OK) مع **JSON** قياسي.
8. يقوم العميل بتحديث واجهته اعتمادًا على الاستجابة.

> تكامل البيانات:
>
> - **MySQL عبر JPA**: كيانات مثل `Patient`, `Doctor`, `Appointment`, `Admin` تُخزن بعلاقات واضحة ومفاتيح خارجية.
> - **MongoDB عبر Spring Data**: وثائق مثل `Prescription`, `Feedback`, `Logs` تُخزن مرنًا بمصفوفات/كائنات متداخلة، ويمكن حفظ مراجع `appointmentId`/`patientId` للربط المنطقي.

---

**Author(s):** IBM Skills Network Team
