package challenge.notification_service.service;

import challenge.notification_service.domain.Notification;
import challenge.notification_service.model.NotificationDTO;
import challenge.notification_service.repos.NotificationRepository;
import challenge.notification_service.util.NotFoundException;
import java.util.List;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;


@Service
public class NotificationService {

    private final NotificationRepository notificationRepository;

    public NotificationService(final NotificationRepository notificationRepository) {
        this.notificationRepository = notificationRepository;
    }

    public List<NotificationDTO> findAll() {
        final List<Notification> notifications = notificationRepository.findAll(Sort.by("id"));
        return notifications.stream()
                .map(notification -> mapToDTO(notification, new NotificationDTO()))
                .toList();
    }

    public NotificationDTO get(final Long id) {
        return notificationRepository.findById(id)
                .map(notification -> mapToDTO(notification, new NotificationDTO()))
                .orElseThrow(NotFoundException::new);
    }

    public Long create(final NotificationDTO notificationDTO) {
        final Notification notification = new Notification();
        mapToEntity(notificationDTO, notification);
        return notificationRepository.save(notification).getId();
    }

    public void update(final Long id, final NotificationDTO notificationDTO) {
        final Notification notification = notificationRepository.findById(id)
                .orElseThrow(NotFoundException::new);
        mapToEntity(notificationDTO, notification);
        notificationRepository.save(notification);
    }

    public void delete(final Long id) {
        notificationRepository.deleteById(id);
    }

    private NotificationDTO mapToDTO(final Notification notification,
            final NotificationDTO notificationDTO) {
        notificationDTO.setId(notification.getId());
        notificationDTO.setIsProcessed(notification.getIsProcessed());
        notificationDTO.setProcessedDate(notification.getProcessedDate());
        notificationDTO.setReservationEventId(notification.getReservationEventId());
        notificationDTO.setType(notification.getType());
        return notificationDTO;
    }

    private Notification mapToEntity(final NotificationDTO notificationDTO,
            final Notification notification) {
        notification.setIsProcessed(notificationDTO.getIsProcessed());
        notification.setProcessedDate(notificationDTO.getProcessedDate());
        notification.setReservationEventId(notificationDTO.getReservationEventId());
        notification.setType(notificationDTO.getType());
        return notification;
    }

}
